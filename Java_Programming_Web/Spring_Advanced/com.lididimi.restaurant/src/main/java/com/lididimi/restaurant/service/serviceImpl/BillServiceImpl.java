package com.lididimi.restaurant.service.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.lididimi.restaurant.model.enums.PaymentMethodNameEnum;
import com.lididimi.restaurant.repository.BillRepository;
import com.lididimi.restaurant.service.BillService;
import com.lididimi.restaurant.utils.RestaurantUtils;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.io.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
@Service
public class BillServiceImpl implements BillService {
    private final JwtFilter jwtFilter;
    private final BillRepository billRepository;

    public BillServiceImpl(JwtFilter jwtFilter, BillRepository billRepository) {
        this.jwtFilter = jwtFilter;
        this.billRepository = billRepository;
    }

    @Override
    public ResponseEntity<String> generateReport(@Valid BillDTO billDTO, BindingResult bindingResult) {
        log.info("Inside generateReport");
        try {
            String fileName;
            if (!bindingResult.hasErrors()) {
                if (billDTO.isGenerate()) {
                    fileName = billDTO.getUuid();
                } else {
                    fileName = RestaurantUtils.getUUID();
                    billDTO.setUuid(fileName);
                    insertBill(billDTO);
                }
                StringBuilder data = new StringBuilder();
                data.append("Name: ");
                data.append(billDTO.getName());
                data.append(System.lineSeparator());
                data.append("Contact Number: ");
                data.append(billDTO.getContactNumber());
                data.append(System.lineSeparator());
                data.append("Email: ");
                data.append(billDTO.getEmail());
                data.append(System.lineSeparator());
                data.append("Payment Method: ");
                data.append(billDTO.getPaymentMethod());

                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(RestaurantConstants.STORE_LOCATION + "\\" + fileName + ".pdf"));

                document.open();
                setRectangleInPdf(document);

                Paragraph chunk = new Paragraph("Restaurant Management System", getFont("Header"));
                chunk.setAlignment(Element.ALIGN_CENTER);
                document.add(chunk);

                Paragraph paragraph = new Paragraph(data + "\n \n", getFont("Data"));
                document.add(paragraph);

                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(100);
                addTableHeader(table);

                JSONArray jsonArray = RestaurantUtils.getJSONArrayFromString((String) billDTO.getProductDetails());

                for (int i = 0; i < jsonArray.length(); i++) {
                    addRows(table, RestaurantUtils.getMapFromJson(jsonArray.getString(i)));
                }
                document.add(table);

                Paragraph footer = new Paragraph("Total: " + billDTO.getTotal() + "\n" + "Thank you for visiting Restaurant Management System.");
                document.add(footer);

                document.close();
                return RestaurantUtils.getResponseEntity((String.format("{\"uuid\": \"%s\"}", fileName)), HttpStatus.OK);
            } else {
                return RestaurantUtils.getResponseEntity("{\"message\": \"Required data not found.\"}", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
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
            bill.setTotal(billDTO.getTotal());
            bill.setProductDetails(billDTO.getProductDetails());
            bill.setCreatedBy((String) jwtFilter.currentUser());
            billRepository.save(bill);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

/*    private boolean validateRequestMap(Map<String, Object> requestMap) {
        return requestMap.containsKey("name") &&
                requestMap.containsKey("contactNumber") &&
                requestMap.containsKey("email") &&
                requestMap.containsKey("paymentMethod") &&
                requestMap.containsKey("productDetails") &&
                requestMap.containsKey("totalAmount");
    }*/

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
    public ResponseEntity<List<BillEntity>> getBills() {
        List<BillEntity> list = new ArrayList<>();
        if (jwtFilter.isAdmin()) {
            log.info("Inside getBills");
            list = billRepository.getAllBills();
        } else {
            list = billRepository.getBillsByUsername(jwtFilter.currentUser());
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap) throws IOException {
        log.info("Inside getPdf : requestMap {}", requestMap);

        byte[] byteArray = new byte[0];
        if (!requestMap.containsKey("uuid")) { // && validateRequestMap(requestMap)
            return new ResponseEntity<>(byteArray, HttpStatus.BAD_REQUEST);
        }
        String filePath = RestaurantConstants.STORE_LOCATION + "\\" + (String) requestMap.get("uuid") + ".pdf";

        if (!RestaurantUtils.isFileExist(filePath)) {
            requestMap.put("isGenerate", false);
            generateReport(requestMap);
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

    @Override
    @Scheduled(cron = "0 0 2 * * ?") // Runs every day at 2 AM
    @Transactional
    public void cleanupOldBillsTask() {
        log.info("Starting cleanup of old bills at {}", Instant.now());
        System.out.println("Current Time Zone: " + java.util.TimeZone.getDefault().getID());
        cleanupOldBills();
    }


    @Transactional
    public void cleanupOldBills() {
        Instant cutoffDate = Instant.now().minusSeconds(30L * 24 * 60 * 60); // cutoff date (30 days ago)
        // Instant cutoffDate = Instant.now().minusSeconds(60);
        billRepository.deleteBillsOlderThan(cutoffDate);
    }

    // Test scheduled task
   /* @Override
    @Scheduled(cron = "0 * * * * ?") // Runs every minute
    @Transactional
    public void cleanupOldBillsTask() {
        Instant cutoffDate = Instant.now().minusSeconds(60); // 1 minute ago
        billRepository.deleteBillsOlderThan(cutoffDate);
        log.info("Old bills cleaned up");
    }*/


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

            // Extract product names from bills
            Map<String, Long> productCountMap = new HashMap<>();
            Gson gson = new Gson();

            for (BillEntity bill : billsByEmail) {
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
                Map<String, Object> productData = new HashMap<>();
                productData.put("productName", entry.getKey());
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
}
