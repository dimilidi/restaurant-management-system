package com.lididimi.restaurant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lididimi.restaurant.model.dto.BestSellerDTO;
import com.lididimi.restaurant.model.dto.GuestDTO;
import com.lididimi.restaurant.model.dto.TopEmployeeDTO;
import com.lididimi.restaurant.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReportControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReportService reportService;

    private List<BestSellerDTO> bestSellers;
    private List<TopEmployeeDTO> topEmployees;
    private List<GuestDTO> guests;

    @BeforeEach
    public void setUp() {
        bestSellers = Arrays.asList(
                new BestSellerDTO("Product1", 100),
                new BestSellerDTO("Product2", 200)
        );

        topEmployees = Arrays.asList(
                new TopEmployeeDTO("email1@mail.com", "Employee1", 100),
                new TopEmployeeDTO("email2@mail.com", "Employee2", 200)
        );

        guests = Arrays.asList(
                new GuestDTO("email1@mail.com", "Guest1", 5L, Arrays.asList("Product1", "Product2")),
                new GuestDTO("email2@mail.com", "Guest2", 3L, Arrays.asList("Product3", "Product4"))
        );
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetBestSellers() throws Exception {
        when(reportService.findBestSellers()).thenReturn(bestSellers);

        mockMvc.perform(get("/reports/best-sellers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", org.hamcrest.Matchers.hasSize(bestSellers.size())))
                .andExpect(jsonPath("$.data[0].name").value("Product1"))
                .andExpect(jsonPath("$.data[0].sales").value(100));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetTopEmployees() throws Exception {
        when(reportService.getTopEmployees()).thenReturn(topEmployees);

        mockMvc.perform(get("/reports/top-employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", org.hamcrest.Matchers.hasSize(topEmployees.size())))
                .andExpect(jsonPath("$.data[0].email").value("email1@mail.com"))
                .andExpect(jsonPath("$.data[0].employeeName").value("Employee1"))
                .andExpect(jsonPath("$.data[0].billCount").value(100));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetRegularGuestsWithFavoriteProducts() throws Exception {
        when(reportService.findRegularGuestsWithFavoriteProducts()).thenReturn(guests);

        mockMvc.perform(get("/reports/regular-guests")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", org.hamcrest.Matchers.hasSize(guests.size())))
                .andExpect(jsonPath("$.data[0].email").value("email1@mail.com"))
                .andExpect(jsonPath("$.data[0].name").value("Guest1"))
                .andExpect(jsonPath("$.data[0].billCount").value(5))
                .andExpect(jsonPath("$.data[0].topProducts", org.hamcrest.Matchers.hasSize(2)))
                .andExpect(jsonPath("$.data[0].topProducts[0]").value("Product1"))
                .andExpect(jsonPath("$.data[0].topProducts[1]").value("Product2"));
    }
}
