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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ReportServiceImplTest {

    @Mock
    private BillRepository billRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ReportServiceImpl reportService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindBestSellers_Success() throws JSONException {
        // Arrange
        String productDetails1 = "[{\"id\": 2, \"name\": \"Pizza Margarita\", \"price\": 2.20, \"total\": 44.80, \"category\": \"Pizza\", \"quantity\": \"2\"}]";
        String productDetails2 = "[{\"id\": 3, \"name\": \"Pasta\", \"price\": 1.50, \"total\": 15.00, \"category\": \"Pasta\", \"quantity\": \"3\"}]";
        BillEntity bill1 = new BillEntity();
        bill1.setProductDetails(productDetails1);
        BillEntity bill2 = new BillEntity();
        bill2.setProductDetails(productDetails2);
        List<BillEntity> bills = Arrays.asList(bill1, bill2);

        when(billRepository.getAllBills()).thenReturn(Optional.of(bills));

        // Act
        List<BestSellerDTO> bestSellers = reportService.findBestSellers();

        // Assert
        assertNotNull(bestSellers);
        assertEquals(2, bestSellers.size());

        // Check if the best-sellers list contains "Pizza Margarita" and "Pasta" with the correct quantities
        Map<String, Integer> expectedBestSellers = new HashMap<>();
        expectedBestSellers.put("Pizza Margarita", 2);
        expectedBestSellers.put("Pasta", 3);

        for (BestSellerDTO bestSeller : bestSellers) {
            assertTrue(expectedBestSellers.containsKey(bestSeller.getName()));
            assertEquals(expectedBestSellers.get(bestSeller.getName()), bestSeller.getSales());
        }
    }


    @Test
    public void testFindBestSellers_EmptyBills() {
        // Arrange
        when(billRepository.getAllBills()).thenReturn(Optional.empty());

        // Act
        List<BestSellerDTO> bestSellers = reportService.findBestSellers();

        // Assert
        assertNotNull(bestSellers);
        assertTrue(bestSellers.isEmpty());
    }

    @Test
    public void testGetTopEmployees_Success() {
        // Arrange
        List<Map<String, Object>> topEmployees = new ArrayList<>();
        Map<String, Object> employee1 = new HashMap<>();
        employee1.put("email", "employee1@example.com");
        employee1.put("employeeName", "Employee One");
        employee1.put("billCount", 10L);
        topEmployees.add(employee1);

        when(billRepository.findTopEmployees(any(Pageable.class))).thenReturn(Optional.of(topEmployees));

        // Act
        List<TopEmployeeDTO> topEmployeesList = reportService.getTopEmployees();

        // Assert
        assertNotNull(topEmployeesList);
        assertEquals(1, topEmployeesList.size());
        assertEquals("employee1@example.com", topEmployeesList.get(0).getEmail());
        assertEquals("Employee One", topEmployeesList.get(0).getEmployeeName());
        assertEquals(10L, topEmployeesList.get(0).getBillCount());
    }

    @Test
    public void testGetTopEmployees_Empty() {
        // Arrange
        when(billRepository.findTopEmployees(any(Pageable.class))).thenReturn(Optional.empty());

        // Act
        List<TopEmployeeDTO> topEmployeesList = reportService.getTopEmployees();

        // Assert
        assertNotNull(topEmployeesList);
        assertTrue(topEmployeesList.isEmpty());
    }

    /*@Test
    public void testFindRegularGuestsWithFavoriteProducts_Success() {
        // Arrange
        LocalDate lastYearDate = LocalDate.now().minusYears(1);
        Instant lastYear = lastYearDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        List<Map<String, Object>> regularGuests = new ArrayList<>();
        Map<String, Object> guest1 = new HashMap<>();
        guest1.put("email", "guest1@example.com");
        guest1.put("name", "Guest One");
        guest1.put("billCount", 400L);
        regularGuests.add(guest1);

        when(billRepository.findRegularGuestsWithAtLeast365Bills(lastYear)).thenReturn(regularGuests);
        when(billRepository.findByEmail(anyString())).thenReturn(Optional.of(Collections.singletonList(new BillEntity())));
        when(modelMapper.map(any(BillEntity.class), eq(BillDTO.class))).thenReturn(new BillDTO());

        // Mock Gson behavior
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "Pizza Margarita");
        jsonObject.addProperty("quantity", "5");
        jsonArray.add(jsonObject);
        when(billRepository.findByEmail(anyString())).thenReturn(Optional.of(Collections.singletonList(new BillEntity() {
            @Override
            public String getProductDetails() {
                return gson.toJson(jsonArray);
            }
        })));

        // Act
        List<GuestDTO> guests = reportService.findRegularGuestsWithFavoriteProducts();

        // Assert
        assertNotNull(guests);
        assertEquals(1, guests.size());
        assertEquals("guest1@example.com", guests.get(0).getEmail());
        assertEquals("Guest One", guests.get(0).getName());
        assertEquals(400L, guests.get(0).getBillCount());
        assertEquals(Collections.singletonList("Pizza Margarita"), guests.get(0).getTopProducts());
    }*/

    @Test
    public void testFindRegularGuestsWithFavoriteProducts_Success() {
        // Arrange
        LocalDate lastYearDate = LocalDate.now().minusYears(1);
        Instant lastYear = lastYearDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        List<Map<String, Object>> regularGuests = new ArrayList<>();
        Map<String, Object> guest1 = new HashMap<>();
        guest1.put("email", "guest1@example.com");
        guest1.put("name", "Guest One");
        guest1.put("billCount", 400L);
        regularGuests.add(guest1);

        when(billRepository.findRegularGuestsWithAtLeast365Bills(lastYear)).thenReturn(regularGuests);

        BillEntity billEntity = new BillEntity();
        String productDetails = "[{\"id\": 2, \"name\": \"Pizza Margarita\", \"price\": 2.20, \"total\": 44.80, \"category\": \"Pizza\", \"quantity\": \"5\"}]";
        billEntity.setProductDetails(productDetails);

        BillDTO billDTO = new BillDTO();
        billDTO.setProductDetails(productDetails);

        when(billRepository.findByEmail(anyString())).thenReturn(Optional.of(Collections.singletonList(billEntity)));
        when(modelMapper.map(any(BillEntity.class), eq(BillDTO.class))).thenReturn(billDTO);

        // Act
        List<GuestDTO> guests = reportService.findRegularGuestsWithFavoriteProducts();

        // Assert
        assertNotNull(guests);
        assertEquals(1, guests.size());
        assertEquals("guest1@example.com", guests.get(0).getEmail());
        assertEquals("Guest One", guests.get(0).getName());
        assertEquals(400L, guests.get(0).getBillCount());
        assertEquals(Collections.singletonList("Pizza Margarita"), guests.get(0).getTopProducts());
    }



    @Test
    public void testFindRegularGuestsWithFavoriteProducts_Empty() {
        // Arrange
        LocalDate lastYearDate = LocalDate.now().minusYears(1);
        Instant lastYear = lastYearDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        when(billRepository.findRegularGuestsWithAtLeast365Bills(lastYear)).thenReturn(Collections.emptyList());

        // Act
        List<GuestDTO> guests = reportService.findRegularGuestsWithFavoriteProducts();

        // Assert
        assertNotNull(guests);
        assertTrue(guests.isEmpty());
    }
}

