package com.lididimi.restaurant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.model.dto.BillDTO;
import com.lididimi.restaurant.model.enums.PaymentMethodNameEnum;
import com.lididimi.restaurant.model.response.ErrorResponse;
import com.lididimi.restaurant.service.BillService;
import com.lididimi.restaurant.service.serviceImpl.BillServiceImpl;
import com.lididimi.restaurant.utils.RestaurantUtils;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BillControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BillServiceImpl billService;

    private BillDTO billDTO;
    @MockBean
    private JwtFilter jwtFilter;

    @BeforeEach
    public void setUp() {
        billDTO = new BillDTO();
        billDTO.setId(1L);
        billDTO.setUuid("123e4567-e89b-12d3-a456-426614174000");
        billDTO.setName("John Doe");
        billDTO.setEmail("johndoe@example.com");
        billDTO.setContactNumber("0123456789");
        billDTO.setPaymentMethod(PaymentMethodNameEnum.CREDIT_CARD);
        billDTO.setTotal(BigDecimal.valueOf(100.0));
        billDTO.setProductDetails("Product details here");
        billDTO.setGenerate(true);
        billDTO.setCreatedBy("admin");
        billDTO.setCreatedDate(Instant.now());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGenerateReport_Success() throws Exception {
        String responseMessage = "Report generated successfully";

        when(billService.generateReport(any(BillDTO.class))).thenReturn(responseMessage);

        mockMvc.perform(MockMvcRequestBuilders.post("/bills/generateReport")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(billDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(responseMessage));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGenerateReport_ValidationError() throws Exception {
        billDTO.setName(""); // Invalid name

        Map<String, String> errors = new HashMap<>();
        errors.put("name", "Name is mandatory");

        mockMvc.perform(MockMvcRequestBuilders.post("/bills/generateReport")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(billDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Name is mandatory"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetPdf_Success() throws Exception {
        byte[] pdf = new byte[]{1, 2, 3};

        when(billService.getPdf(any(BillDTO.class))).thenReturn(pdf);

        mockMvc.perform(MockMvcRequestBuilders.post("/bills/getPdf")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(billDTO)))
                .andExpect(status().isOk())
                .andExpect(content().bytes(pdf));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetPdf_Failure() throws Exception {
        when(billService.getPdf(any(BillDTO.class))).thenThrow(new RuntimeException("Failed to generate PDF"));

        mockMvc.perform(MockMvcRequestBuilders.post("/bills/getPdf")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(billDTO)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .andExpect(jsonPath("$.message").value("Failed to generate PDF"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDelete_Success() throws Exception {
        String responseMessage = "Bill deleted successfully";

        when(billService.delete(1L)).thenReturn(responseMessage);

        mockMvc.perform(MockMvcRequestBuilders.delete("/bills/delete/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(responseMessage));
    }

    @Test
    public void testGetBills_AsAdmin() throws Exception {
        when(jwtFilter.isAdmin()).thenReturn(true);

        List<BillDTO> bills = Arrays.asList(billDTO);

        when(billService.getBills()).thenReturn(bills);

        mockMvc.perform(MockMvcRequestBuilders.get("/bills/getBills"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].uuid").value("123e4567-e89b-12d3-a456-426614174000"))
                .andExpect(jsonPath("$.data[0].name").value("John Doe"))
                .andExpect(jsonPath("$.data[0].email").value("johndoe@example.com"))
                .andExpect(jsonPath("$.data[0].contactNumber").value("0123456789"))
                .andExpect(jsonPath("$.data[0].paymentMethod").value("CREDIT_CARD"))
                .andExpect(jsonPath("$.data[0].total").value(100.0))
                .andExpect(jsonPath("$.data[0].productDetails").value("Product details here"))
                .andExpect(jsonPath("$.data[0].isGenerate").value(true))
                .andExpect(jsonPath("$.data[0].createdBy").value("admin"))
                .andExpect(jsonPath("$.data[0].createdDate").exists());
    }

    @Test
    public void testGetBills_AsUser() throws Exception {
        when(jwtFilter.isAdmin()).thenReturn(false);
        when(jwtFilter.currentUser()).thenReturn("John Doe");

        List<BillDTO> bills = Arrays.asList(billDTO);

        when(billService.getBills()).thenReturn(bills);

        mockMvc.perform(MockMvcRequestBuilders.get("/bills/getBills"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].uuid").value("123e4567-e89b-12d3-a456-426614174000"))
                .andExpect(jsonPath("$.data[0].name").value("John Doe"))
                .andExpect(jsonPath("$.data[0].email").value("johndoe@example.com"))
                .andExpect(jsonPath("$.data[0].contactNumber").value("0123456789"))
                .andExpect(jsonPath("$.data[0].paymentMethod").value("CREDIT_CARD"))
                .andExpect(jsonPath("$.data[0].total").value(100.0))
                .andExpect(jsonPath("$.data[0].productDetails").value("Product details here"))
                .andExpect(jsonPath("$.data[0].isGenerate").value(true))
                .andExpect(jsonPath("$.data[0].createdBy").value("admin"))
                .andExpect(jsonPath("$.data[0].createdDate").exists());
    }
}
