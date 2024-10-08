package com.lididimi.restaurant.service.serviceImpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lididimi.restaurant.config.RetentionProperties;
import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.security.JwtFilter;
import com.lididimi.restaurant.model.dto.bill.BillDTO;
import com.lididimi.restaurant.model.entity.BillEntity;
import com.lididimi.restaurant.repository.BillRepository;
import com.lididimi.restaurant.service.BillService;
import com.lididimi.restaurant.utils.RestaurantUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.IOUtils;
import org.json.JSONArray;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.net.URL;
import java.time.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
@Service
public class BillServiceImpl implements BillService {
    private final Clock clock;
    private final JwtFilter jwtFilter;
    private final BillRepository billRepository;
    private final ModelMapper modelMapper;
    private final Cloudinary cloudinary;
    private final RetentionProperties retentionProperties;


    public BillServiceImpl(
            Clock clock,
            JwtFilter jwtFilter,
            BillRepository billRepository,
            ModelMapper modelMapper,
            Cloudinary cloudinary, RetentionProperties retentionProperties
    ) {
        this.retentionProperties = retentionProperties;
        this.clock = clock != null ? clock : Clock.systemUTC();
        this.jwtFilter = jwtFilter;
        this.billRepository = billRepository;
        this.modelMapper = modelMapper;
        this.cloudinary = cloudinary;
    }


    @Override
    public String generateReport(BillDTO billDTO) {
        log.info("Inside generateReport");
        try {
            String fileName = billDTO.isGenerate() ? billDTO.getUuid() : createAndInsertBill(billDTO);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();
            addContentToDocument(document, billDTO);
            document.close();
            uploadPdfToCloudinary(fileName, baos.toByteArray());
            return String.format("{\"uuid\": \"%s\"}", fileName);
        } catch (Exception e) {
            log.error("Error generating report: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    @Override
    public byte[] getPdf(BillDTO billDTO) throws IOException {
        log.info("Inside getPdf : requestMap {}", billDTO);

        byte[] byteArray = new byte[0];
        if (billDTO.getUuid() == null) {
            return byteArray;
        }
        String fileName = billDTO.getUuid();
        byteArray = downloadPdfFromCloudinary(fileName);
        return byteArray;
    }

    @Override
    public List<BillDTO> getBills() {
        Optional<List<BillEntity>> bills;
        if (jwtFilter.isAdmin()) {
            log.info("Inside getBills as Admin");
            bills = billRepository.getAllBills();
        } else {
            log.info("Inside getBills as User");
            bills = billRepository.getBillsByUsername(jwtFilter.currentUser());
        }

        List<BillDTO> billDTOs = bills.get().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return billDTOs;
    }


    @Override
    public String delete(Long id) {
        Optional<BillEntity> optionalBill = billRepository.findById(id);
        if (optionalBill.isPresent()) {
            billRepository.deleteById(id);
            return RestaurantConstants.PRODUCT_DELETE_SUCCESS;
        } else {
            return RestaurantConstants.CATEGORY_NOT_FOUND;
        }
    }

    @Override
    public void cleanupOldBills() {
        LocalDate now = LocalDate.now(clock);

        LocalDate cutoffLocalDate = now.minus(retentionProperties.getPeriod());
        Instant cutoffDate = cutoffLocalDate.atStartOfDay(ZoneId.of(clock.getZone().getId())).toInstant();

        log.info("Removing all bills older than " + cutoffDate);

        billRepository.deleteBillsOlderThan(cutoffDate);
    }


    private String createAndInsertBill(BillDTO billDTO) {
        String fileName = RestaurantUtils.getUUID();
        billDTO.setUuid(fileName);
        billDTO.setCreatedBy(jwtFilter.currentUser());
        billDTO.setCreatedDate(Instant.now());
        insertBill(billDTO);
        return fileName;
    }

    private void addContentToDocument(Document document, BillDTO billDTO) throws Exception {
        Paragraph header = new Paragraph("Restaurant Management System", getFont("Header"));
        header.setAlignment(Element.ALIGN_CENTER);
        document.add(header);

        document.add(new Paragraph(formatBillData(billDTO), getFont("Data")));
        document.add(new Paragraph("\n"));
        addTableToDocument(document, billDTO);

        document.add(new Paragraph("Total: " + billDTO.getTotal() + "\nThank you for visiting Restaurant Management System."));
    }

    private String formatBillData(BillDTO billDTO) {
        return String.format("Name: %s%nContact Number: %s%nEmail: %s%nPayment Method: %s%n",
                billDTO.getName(), billDTO.getContactNumber(), billDTO.getEmail(), billDTO.getPaymentMethod());
    }

    private void addTableToDocument(Document document, BillDTO billDTO) throws Exception {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        addTableHeader(table);
        JSONArray jsonArray = RestaurantUtils.getJSONArrayFromString((String) billDTO.getProductDetails());
        for (int i = 0; i < jsonArray.length(); i++) {
            addRows(table, RestaurantUtils.getMapFromJson(jsonArray.getString(i)));
        }
        document.add(table);
    }

    private void addRows(PdfPTable table, Map<String, Object> data) {
        log.info("Inside addRows");

        table.addCell(data.get("name") != null ? data.get("name").toString() : "");
        table.addCell(data.get("category") != null ? data.get("category").toString() : "");
        table.addCell(data.get("quantity") != null ? data.get("quantity").toString() : "");
        table.addCell(data.get("price") != null ? data.get("price").toString() : "");
        table.addCell(data.get("total") != null ? data.get("total").toString() : "");
    }

    private void addTableHeader(PdfPTable table) {
        log.info("Inside addTableHeader");
        Stream.of("Name", "Category", "Quantity", "Price", "Sub Total").forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(columnTitle));
            header.setBackgroundColor(BaseColor.YELLOW);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setVerticalAlignment(Element.ALIGN_CENTER);
            table.addCell(header);
        });
    }

    private void setRectangleInPdf(Document document) throws DocumentException {
        log.info("Inside setRectangleInPdf");
        Rectangle rectangle = new Rectangle(577, 825, 18, 15);
        rectangle.enableBorderSide(1);
        rectangle.enableBorderSide(2);
        rectangle.enableBorderSide(4);
        rectangle.enableBorderSide(8);
        rectangle.setBorderColor(BaseColor.BLACK);
        rectangle.setBorderWidth(1);
        document.add(rectangle);
    }

    private void insertBill(BillDTO billDTO) {
        try {
            BillEntity bill = new BillEntity();
            bill.setUuid(billDTO.getUuid());
            bill.setName(billDTO.getName());
            bill.setEmail(billDTO.getEmail());
            bill.setContactNumber(billDTO.getContactNumber());
            bill.setPaymentMethod(billDTO.getPaymentMethod());
            bill.setTotal(billDTO.getTotal());
            bill.setProductDetails(billDTO.getProductDetails());
            bill.setCreatedBy(billDTO.getCreatedBy());
            bill.setCreatedDate(billDTO.getCreatedDate());
            billRepository.save(bill);
            log.info("Inside insertBill {}", bill);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private Font getFont(String type) {
        log.info("Inside getFont");

        switch (type) {
            case "Header":
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 18, BaseColor.BLACK);
                headerFont.setStyle(Font.BOLD);
                return headerFont;
            case "Data":
                Font headerData = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.BLACK);
                headerData.setStyle(Font.BOLD);
                return headerData;
            default:
                return new Font();
        }
    }

    byte[] getByteArray(String filePath) throws IOException {
        File initialFile = new File(filePath);

        InputStream targetStream = new FileInputStream(initialFile);
        byte[] byteArray = IOUtils.toByteArray(targetStream);
        targetStream.close();
        return byteArray;
    }

    private BillDTO convertToDTO(BillEntity billEntity) {
        return modelMapper.map(billEntity, BillDTO.class);
    }


    private void uploadPdfToCloudinary(String fileName, byte[] pdfBytes) throws IOException {
        try {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(pdfBytes, ObjectUtils.asMap("public_id", fileName, "resource_type", "raw"));
            log.info("PDF uploaded to Cloudinary: {}", uploadResult);
        } catch (Exception e) {
            log.error("Error uploading PDF to Cloudinary", e);
            throw new IOException("Error uploading PDF to Cloudinary", e);
        }
    }

    private byte[] downloadPdfFromCloudinary(String fileName) throws IOException {
        try {
            Map<String, Object> result = cloudinary.api().resource(fileName, ObjectUtils.asMap("resource_type", "raw"));
            String url = (String) result.get("url");
            return IOUtils.toByteArray(new URL(url).openStream());
        } catch (Exception e) {
            log.error("Error downloading PDF from Cloudinary", e);
            throw new IOException("Error downloading PDF from Cloudinary", e);
        }
    }
}
