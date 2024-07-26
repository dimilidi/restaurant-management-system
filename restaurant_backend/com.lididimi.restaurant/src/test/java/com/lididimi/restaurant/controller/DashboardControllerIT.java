package com.lididimi.restaurant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lididimi.restaurant.service.DashboardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DashboardControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DashboardService dashboardService;

    private Map<String, Object> countMap;

    @BeforeEach
    public void setUp() {
        countMap = new HashMap<>();
        countMap.put("category", 10);
        countMap.put("product", 20);
        countMap.put("bill", 30);

        when(dashboardService.getCount()).thenReturn(countMap);
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetCount() throws Exception {
        mockMvc.perform(get("/dashboard/details")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(HttpStatus.OK.value())) // Assuming the status field is in SuccessResponse
                .andExpect(jsonPath("$.message").value("")) // Assuming the message field is empty
                .andExpect(jsonPath("$.data.category").value(10))
                .andExpect(jsonPath("$.data.product").value(20))
                .andExpect(jsonPath("$.data.bill").value(30));
    }
}
