package com.lididimi.restaurant.service.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.model.entity.BillEntity;
import com.lididimi.restaurant.repository.BillRepository;
import com.lididimi.restaurant.service.BillService;
import com.lididimi.restaurant.utils.RestaurantUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.List;
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
    public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
        log.info("Inside generateReport");
        try {
            String fileName;
            if (validateRequestMap(requestMap)) {
                if (requestMap.containsKey("isGenerate") && !((Boolean) requestMap.get("isGenerate"))) {
                    fileName = (String) requestMap.get("uuid");
                } else {
                    fileName = RestaurantUtils.getUUID();
                    requestMap.put("uuid", fileName);
                    insertBill(requestMap);
                }
                StringBuilder data = new StringBuilder();
                data.append("Name: ");
                data.append(requestMap.get("name"));
                data.append(System.lineSeparator());
                data.append("Contact Number: ");
                data.append(requestMap.get("contactNumber"));
                data.append(System.lineSeparator());
                data.append("Email: ");
                data.append(requestMap.get("email"));
                data.append(System.lineSeparator());
                data.append("Payment Method: ");
                data.append(requestMap.get("paymentMethod"));

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

                JSONArray jsonArray = RestaurantUtils.getJSONArrayFromString((String) requestMap.get("productDetails"));

                for (int i = 0; i < jsonArray.length(); i++) {
                    addRows(table, RestaurantUtils.getMapFromJson(jsonArray.getString(i)));
                }
                document.add(table);

                Paragraph footer = new Paragraph("Total: " + requestMap.get("totalAmount") + "\n" + "Thank you for visiting Restaurant Management System.");
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

    private void insertBill(Map<String, Object> requestMap) {
        try {
            BillEntity bill = new BillEntity();
            bill.setUuid((String) requestMap.get("uuid"));
            bill.setName((String) requestMap.get("name"));
            bill.setEmail((String) requestMap.get("email"));
            bill.setContactNumber((String) requestMap.get("contactNumber"));
            bill.setPaymentMethod((String) requestMap.get("paymentMethod"));
            bill.setTotal(new BigDecimal((String) requestMap.get("totalAmount")));
            bill.setProductDetails((String) requestMap.get("productDetails"));
            bill.setCreatedBy((String) jwtFilter.currentUser());
            billRepository.save(bill);
        } catch (Exception e) {

        }
    }


    private boolean validateRequestMap(Map<String, Object> requestMap) {
        return requestMap.containsKey("name") &&
                requestMap.containsKey("contactNumber") &&
                requestMap.containsKey("email") &&
                requestMap.containsKey("paymentMethod") &&
                requestMap.containsKey("productDetails") &&
                requestMap.containsKey("totalAmount");
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
        if (!requestMap.containsKey("uuid") && validateRequestMap(requestMap)) {
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
        //Instant cutoffDate = Instant.now().minusSeconds(30L * 24 * 60 * 60); // cutoff date (30 days ago)
        Instant cutoffDate = Instant.now().minusSeconds(60);
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

        log.info(bestSellers.toString());

        return bestSellers;
    }

    @Override
    public List<Map<String, Object>> getTopEmployees() {
        return billRepository.findTopEmployees();
    }

}
