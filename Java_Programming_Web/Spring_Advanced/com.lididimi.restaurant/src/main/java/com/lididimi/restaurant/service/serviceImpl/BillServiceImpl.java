package com.lididimi.restaurant.service.serviceImpl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.model.dto.BillDTO;
import com.lididimi.restaurant.model.entity.BillEntity;
import com.lididimi.restaurant.repository.BillRepository;
import com.lididimi.restaurant.service.BillService;
import com.lididimi.restaurant.utils.RestaurantUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
@Service
public class BillServiceImpl implements BillService {
    private final JwtFilter jwtFilter;
    private final BillRepository billRepository;
    private final ModelMapper modelMapper;

    public BillServiceImpl(JwtFilter jwtFilter, BillRepository billRepository, ModelMapper modelMapper) {
        this.jwtFilter = jwtFilter;
        this.billRepository = billRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String generateReport(BillDTO billDTO) {
        log.info("Inside generateReport");
        try {
            String fileName = billDTO.isGenerate() ? billDTO.getUuid() : createAndInsertBill(billDTO);
            Document document = initializeDocument(fileName);
            addContentToDocument(document, billDTO);
            document.close();
            return fileName;
        } catch (Exception e) {
            log.error("Error generating report: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private String createAndInsertBill(BillDTO billDTO) {
        String fileName = RestaurantUtils.getUUID();
        billDTO.setUuid(fileName);
        billDTO.setCreatedBy(jwtFilter.currentUser());
        billDTO.setCreatedDate(Instant.now());
        insertBill(billDTO);
        return fileName;
    }

    private Document initializeDocument(String fileName) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(RestaurantConstants.STORE_LOCATION + "\\" + fileName + ".pdf"));
        document.open();
        setRectangleInPdf(document);
        return document;
    }

    private void addContentToDocument(Document document, BillDTO billDTO) throws Exception {
        Paragraph header = new Paragraph("Restaurant Management System", getFont("Header"));
        header.setAlignment(Element.ALIGN_CENTER);
        document.add(header);

        document.add(new Paragraph(formatBillData(billDTO), getFont("Data")));
        document.add(new Paragraph("\n"));
        addTableToDocument(document, billDTO);

        document.add(new Paragraph("Total: " + billDTO.getTotalAmount() + "\nThank you for visiting Restaurant Management System."));
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
        table.addCell((String) data.get("name"));
        table.addCell((String) data.get("category"));
        table.addCell((String) data.get("quantity"));
        table.addCell(Double.toString((Double) data.get("price")));
        table.addCell(Double.toString((Double) data.get("total")));
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
            bill.setTotal(billDTO.getTotalAmount());
            bill.setProductDetails(billDTO.getProductDetails());
            bill.setCreatedBy(billDTO.getCreatedBy());
            bill.setCreatedDate(billDTO.getCreatedDate());
            billRepository.save(bill);
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

    @Override
    public ResponseEntity<List<BillDTO>> getBills() {
        List<BillEntity> bills;
        if (jwtFilter.isAdmin()) {
            log.info("Inside getBills as Admin");
            bills = billRepository.getAllBills();
        } else {
            log.info("Inside getBills as User");
            bills = billRepository.getBillsByUsername(jwtFilter.currentUser());
        }

        List<BillDTO> billDTOs = bills.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(billDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<byte[]> getPdf(BillDTO billDTO) throws IOException {
        log.info("Inside getPdf : requestMap {}", billDTO);


        byte[] byteArray = new byte[0];
        if (billDTO.getUuid() == null) { // && validateRequestMap(requestMap)
            return new ResponseEntity<>(byteArray, HttpStatus.BAD_REQUEST);
        }
        String filePath = RestaurantConstants.STORE_LOCATION + "\\" + (String) billDTO.getUuid() + ".pdf";

        if (!RestaurantUtils.isFileExist(filePath)) {
            billDTO.setGenerate(false);
            generateReport(billDTO);
        }
        byteArray = getByteArray(filePath);
        return new ResponseEntity<>(byteArray, HttpStatus.OK);
    }


    private byte[] getByteArray(String filePath) throws IOException {
        File initialFile = new File(filePath);

        InputStream targetStream = new FileInputStream(initialFile);
        byte[] byteArray = IOUtils.toByteArray(targetStream);
        targetStream.close();
        return byteArray;
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try {
            Optional<BillEntity> optionalBill = billRepository.findById(id);

            if (optionalBill.isPresent()) {
                billRepository.deleteById(id);
                return RestaurantUtils.getResponseEntity("{\"message\": \"Successfully deleted bill.\"}", HttpStatus.OK);

            } else {
                return RestaurantUtils.getResponseEntity("{\"message\": \"Bill does not exist.\"}", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public List<Map<String, Object>> findBestSellers() {
        List<BillEntity> bills = billRepository.getAllBills();
        Map<String, Integer> productSales = new HashMap<>();

        for (BillEntity bill : bills) {
            try {
                JSONArray products = new JSONArray(bill.getProductDetails());
                for (int i = 0; i < products.length(); i++) {
                    JSONObject product = products.getJSONObject(i);
                    String productName = product.getString("name");
                    int quantity = product.getInt("quantity");

                    productSales.put(productName, productSales.getOrDefault(productName, 0) + quantity);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // Convert to a list of maps for returning
        List<Map<String, Object>> bestSellers = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : productSales.entrySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", entry.getKey());
            map.put("sales", entry.getValue());
            bestSellers.add(map);
        }

        // Sort the list by sales in descending order
        bestSellers.sort((a, b) -> (int) b.get("sales") - (int) a.get("sales"));

        return bestSellers.stream().limit(5).collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> getTopEmployees() {
        Pageable limit = PageRequest.of(0, 5);
        return billRepository.findTopEmployees(limit);
    }

    @Override
    public List<Map<String, Object>> findRegularGuestsWithFavoriteProducts() {
        LocalDate lastYearDate = LocalDate.now().minusYears(1);
        Instant lastYear = lastYearDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        List<Map<String, Object>> regularGuests = billRepository.findRegularGuestsWithAtLeast365Bills(lastYear);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Map<String, Object> guest : regularGuests) {
            String email = (String) guest.get("email");
            List<BillEntity> billsByEmail = billRepository.findByEmail(email);

            List<BillDTO> billDTOs = billsByEmail.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());

            // Extract product names from bills
            Map<String, Long> productCountMap = new HashMap<>();
            Gson gson = new Gson();

            for (BillDTO bill : billDTOs) {
                try {
                    String productDetailsJson = bill.getProductDetails();
                    JsonArray productArray = gson.fromJson(productDetailsJson, JsonArray.class);

                    for (JsonElement productElement : productArray) {
                        JsonObject productNode = productElement.getAsJsonObject();
                        String productName = productNode.get("name").getAsString();
                        productCountMap.put(productName, productCountMap.getOrDefault(productName, 0L) + productNode.get("quantity").getAsInt());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Get top 3 products by count
            List<Map.Entry<String, Long>> topProducts = productCountMap.entrySet().stream()
                    .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                    .limit(3)
                    .collect(Collectors.toList());

            List<String> topProductsList = new ArrayList<>();
            for (Map.Entry<String, Long> entry : topProducts) {
                topProductsList.add(entry.getKey());
            }

            Map<String, Object> guestData = new HashMap<>();
            guestData.put("email", email);
            guestData.put("name", guest.get("name"));
            guestData.put("billCount", guest.get("billCount"));
            guestData.put("topProducts", topProductsList);
            result.add(guestData);
        }
        return result;
    }

    private BillDTO convertToDTO(BillEntity billEntity) {
        return modelMapper.map(billEntity, BillDTO.class);
    }
}
