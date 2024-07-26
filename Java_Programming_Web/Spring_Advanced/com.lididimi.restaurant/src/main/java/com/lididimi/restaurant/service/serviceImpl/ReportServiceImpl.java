package com.lididimi.restaurant.service.serviceImpl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lididimi.restaurant.model.dto.BestSellerDTO;
import com.lididimi.restaurant.model.dto.BillDTO;
import com.lididimi.restaurant.model.dto.GuestDTO;
import com.lididimi.restaurant.model.dto.TopEmployeeDTO;
import com.lididimi.restaurant.model.entity.BillEntity;
import com.lididimi.restaurant.repository.BillRepository;
import com.lididimi.restaurant.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {
    private final BillRepository billRepository;
    private final ModelMapper modelMapper;

    public ReportServiceImpl(BillRepository billRepository, ModelMapper modelMapper) {
        this.billRepository = billRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BestSellerDTO> findBestSellers() {
        // Initialize a map to keep track of product sales
        Map<String, Integer> productSales = new HashMap<>();

        // Retrieve bills and process them
        List<BillEntity> bills = billRepository.getAllBills().orElse(Collections.emptyList());

        for (BillEntity bill : bills) {
            try {
                JSONArray products = new JSONArray(bill.getProductDetails());
                for (int i = 0; i < products.length(); i++) {
                    JSONObject product = products.getJSONObject(i);
                    String productName = product.getString("name");
                    int quantity = product.getInt("quantity");

                    // Update the sales count in the map
                    productSales.put(productName, productSales.getOrDefault(productName, 0) + quantity);
                }
            } catch (JSONException e) {
                log.error("Failed to parse product details for bill ID: " + bill.getId(), e);
                e.printStackTrace();
            }
        }

        // Convert to a list of BestSellerDTO for returning
        return productSales.entrySet().stream()
                .map(entry -> new BestSellerDTO(entry.getKey(), entry.getValue()))
                .sorted((a, b) -> Integer.compare(b.getSales(), a.getSales()))
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public List<TopEmployeeDTO> getTopEmployees() {
        Pageable limit = PageRequest.of(0, 5);
        List<Map<String, Object>> topEmployees = billRepository.findTopEmployees(limit).orElse(Collections.emptyList());

        return topEmployees.stream()
                .map(employee -> new TopEmployeeDTO(
                        (String) employee.get("email"),
                        (String) employee.get("employeeName"),
                        (Long) employee.get("billCount")
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<GuestDTO> findRegularGuestsWithFavoriteProducts() {
        LocalDate lastYearDate = LocalDate.now().minusYears(1);
        Instant lastYear = lastYearDate.atStartOfDay(ZoneId.systemDefault()).toInstant();

        List<Map<String, Object>> regularGuests = billRepository.findRegularGuestsWithAtLeast365Bills(lastYear);
        List<GuestDTO> result = new ArrayList<>();

        for (Map<String, Object> guest : regularGuests) {
            String email = (String) guest.get("email");
            List<BillDTO> billDTOs = fetchBillDTOsByEmail(email);
            Map<String, Long> productCountMap = getProductCountMap(billDTOs);
            List<String> topProductsList = getTopProducts(productCountMap);

            GuestDTO guestDTO = new GuestDTO(
                    (String) guest.get("email"),
                    (String) guest.get("name"),
                    (Long) guest.get("billCount"),
                    topProductsList
            );
            result.add(guestDTO);
        }
        return result;
    }


    private List<BillDTO> fetchBillDTOsByEmail(String email) {
        return billRepository.findByEmail(email)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Map<String, Long> getProductCountMap(List<BillDTO> billDTOs) {
        Map<String, Long> productCountMap = new HashMap<>();
        Gson gson = new Gson();

        for (BillDTO bill : billDTOs) {
            try {
                JsonArray productArray = gson.fromJson(bill.getProductDetails(), JsonArray.class);
                for (JsonElement productElement : productArray) {
                    JsonObject productNode = productElement.getAsJsonObject();
                    String productName = productNode.get("name").getAsString();
                    long quantity = productNode.get("quantity").getAsInt();
                    productCountMap.merge(productName, quantity, Long::sum);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return productCountMap;
    }

    private List<String> getTopProducts(Map<String, Long> productCountMap) {
        return productCountMap.entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private BillDTO convertToDTO(BillEntity billEntity) {
        return modelMapper.map(billEntity, BillDTO.class);
    }
}
