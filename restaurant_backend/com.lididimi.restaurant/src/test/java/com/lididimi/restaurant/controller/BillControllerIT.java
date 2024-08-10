package com.lididimi.restaurant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.model.dto.bill.BillDTO;
import com.lididimi.restaurant.model.enums.PaymentMethodNameEnum;
import com.lididimi.restaurant.repository.BillRepository;
import com.lididimi.restaurant.service.BillService;
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
import java.math.BigDecimal;
import java.util.Collections;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = {
        "smtp_username=dummyUsername",
        "smtp_password=dummyPassword",
})
@AutoConfigureMockMvc
public class BillControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BillService billService;

    @Autowired
    private ObjectMapper objectMapper;

    private BillDTO sampleBillDTO;

    @MockBean
    private BillRepository billRepository;

    @BeforeEach
    public void setUp() {
        sampleBillDTO = new BillDTO();

        // Example setups for `getBills`
        when(billService.getBills()).thenReturn(Collections.singletonList(sampleBillDTO));

        // Example setups for `delete`
        when(billService.delete(1L)).thenReturn(RestaurantConstants.BILL_DELETE_SUCCESS);
        doThrow(new RuntimeException("Deletion error")).when(billService).delete(2L);
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGenerateReportSuccess() throws Exception {
        // Arrange
        BillDTO billDTO = new BillDTO();
        billDTO.setTotal(new BigDecimal("100"));
        billDTO.setName("John Doe");
        billDTO.setPaymentMethod(PaymentMethodNameEnum.DEBIT_CARD);
        billDTO.setProductDetails("[{\"name\": \"Product1\", \"category\": \"Category1\", \"quantity\": 1, \"price\": 10.0, \"total\": 10.0}]");
        billDTO.setEmail("john.doe@example.com");
        billDTO.setGenerate(true);

        String fileName = "test-report"; // Simulated file name
        String jsonResponse = String.format("{\"uuid\": \"%s\"}", fileName);

        // Mock the service method
        when(billService.generateReport(billDTO)).thenReturn(jsonResponse);

        // Act & Assert
        mockMvc.perform(post("/bills/generateReport")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(billDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(fileName));
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteBillSuccess() throws Exception {
        mockMvc.perform(delete("/bills/delete/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(RestaurantConstants.BILL_DELETE_SUCCESS))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteBillFailure() throws Exception {
        mockMvc.perform(delete("/bills/delete/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.message").value("An unexpected error occurred."))
                .andExpect(jsonPath("$.data").doesNotExist());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetBillsSuccess() throws Exception {
        mockMvc.perform(get("/bills/getBills")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.data[0]").isNotEmpty());
    }
}
