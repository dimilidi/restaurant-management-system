package com.lididimi.restaurant.service.serviceImpl;/*

package com.lididimi.restaurant.service.serviceImpl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.model.dto.BillDTO;
import com.lididimi.restaurant.model.entity.BillEntity;
import com.lididimi.restaurant.model.enums.PaymentMethodNameEnum;
import com.lididimi.restaurant.repository.BillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BillServiceImplTest {

    @Mock
    private JwtFilter jwtFilter;

    @Mock
    private BillRepository billRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BillServiceImpl billService;

    private BillDTO billDTO;

    @BeforeEach
    public void setUp() {
        billDTO = new BillDTO();
        billDTO.setUuid("12345");
        billDTO.setName("John Doe");
        billDTO.setEmail("john.doe@example.com");
        billDTO.setContactNumber("0123456789");
        billDTO.setPaymentMethod(PaymentMethodNameEnum.DEBIT_CARD);
        billDTO.setTotal(BigDecimal.valueOf(100.00));
        billDTO.setProductDetails("[{\"name\": \"Pizza\", \"category\": \"Food\", \"quantity\": \"2\", \"price\": \"20.00\", \"total\": \"40.00\"}]");
        billDTO.setGenerate(false);
        billDTO.setCreatedBy("admin");
        billDTO.setCreatedDate(Instant.now());
    }

    @Test
    public void testGenerateReport_Success() {
        // Mocking
        when(jwtFilter.currentUser()).thenReturn("admin");
        when(billRepository.save(any(BillEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        String result = billService.generateReport(billDTO);

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("\"uuid\": \"12345\""));

        // Verify the file creation
        String filePath = RestaurantConstants.STORE_LOCATION + "\\" + billDTO.getUuid() + ".pdf";
        File file = new File(filePath);
        assertTrue(file.exists());

        // Clean up the created file
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testGenerateReport_Exception() {
        // Mocking
        when(jwtFilter.currentUser()).thenReturn("admin");

        // Simulate an exception during document creation
        try {
            doThrow(new DocumentException()).when(billService).initializeDocument(anyString());
        } catch (Exception e) {
            // Ignored for setup
        }

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            billService.generateReport(billDTO);
        });
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
    }

    // Helper method to create mock PDF document
    private Document mockDocument(String fileName) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(RestaurantConstants.STORE_LOCATION + "\\" + fileName + ".pdf"));
        document.open();
        document.add(new Paragraph("Test PDF Content"));
        document.close();
        return document;
    }

    @Test
    public void testGetBillsAsAdmin() {
        BillEntity billEntity = new BillEntity();
        BillDTO billDTO = new BillDTO();
        billDTO.setName("Test");

        when(jwtFilter.isAdmin()).thenReturn(true);
        when(billRepository.getAllBills()).thenReturn(Optional.of(Collections.singletonList(billEntity)));
        when(modelMapper.map(any(BillEntity.class), eq(BillDTO.class))).thenReturn(billDTO);

        assertEquals(Collections.singletonList(billDTO), billService.getBills());

        // Verify interactions
        verify(billRepository, times(1)).getAllBills();
    }

}
*/


import com.itextpdf.text.DocumentException;
import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.model.dto.BillDTO;
import com.lididimi.restaurant.model.entity.BillEntity;
import com.lididimi.restaurant.model.enums.PaymentMethodNameEnum;
import com.lididimi.restaurant.repository.BillRepository;
import com.lididimi.restaurant.service.serviceImpl.BillServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BillServiceImplTest {

    @Mock
    private JwtFilter jwtFilter;

    @Mock
    private BillRepository billRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BillServiceImpl billService;

    private BillDTO billDTO;

    @BeforeEach
    public void setUp() {
        billDTO = new BillDTO();
        billDTO.setUuid("12345");
        billDTO.setName("John Doe");
        billDTO.setEmail("john.doe@example.com");
        billDTO.setContactNumber("0123456789");
        billDTO.setPaymentMethod(PaymentMethodNameEnum.DEBIT_CARD);
        billDTO.setTotal(BigDecimal.valueOf(100.00));
        billDTO.setProductDetails("[{\"name\": \"Pizza\", \"category\": \"Food\", \"quantity\": \"2\", \"price\": \"20.00\", \"total\": \"40.00\"}]");
        billDTO.setGenerate(false);
        billDTO.setCreatedBy("admin");
        billDTO.setCreatedDate(Instant.now());
    }



   @Test
    public void testGetBillsAsAdmin() {
        BillEntity billEntity = new BillEntity();
        BillDTO billDTO = new BillDTO();
        billDTO.setName("Test");

        when(jwtFilter.isAdmin()).thenReturn(true);
        when(billRepository.getAllBills()).thenReturn(Optional.of(Collections.singletonList(billEntity)));
        when(modelMapper.map(any(BillEntity.class), eq(BillDTO.class))).thenReturn(billDTO);

        assertEquals(Collections.singletonList(billDTO), billService.getBills());

        // Verify interactions
        verify(billRepository, times(1)).getAllBills();
    }

    @Test
    public void testDelete_Success() {
        // Arrange
        Long id = 1L;
        BillEntity billEntity = new BillEntity(); // Create a dummy BillEntity

        when(billRepository.findById(id)).thenReturn(Optional.of(billEntity)); // Simulate finding the BillEntity
        doNothing().when(billRepository).deleteById(id); // Simulate successful deletion

        // Act
        String result = billService.delete(id);

        // Assert
        assertEquals(RestaurantConstants.PRODUCT_DELETE_SUCCESS, result);
        verify(billRepository, times(1)).findById(id); // Verify findById was called once
        verify(billRepository, times(1)).deleteById(id); // Verify deleteById was called once
    }

    @Test
    public void testDelete_NotFound() {
        // Arrange
        Long id = 1L;

        when(billRepository.findById(id)).thenReturn(Optional.empty()); // Simulate the entity not being found

        // Act
        String result = billService.delete(id);

        // Assert
        assertEquals(RestaurantConstants.CATEGORY_NOT_FOUND, result);
        verify(billRepository, times(1)).findById(id); // Verify findById was called once
        verify(billRepository, never()).deleteById(id); // Verify deleteById was never called
    }


}












/*

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.model.dto.BillDTO;
import com.lididimi.restaurant.model.entity.BillEntity;
import com.lididimi.restaurant.model.enums.PaymentMethodNameEnum;
import com.lididimi.restaurant.repository.BillRepository;
import com.lididimi.restaurant.service.serviceImpl.BillServiceImpl;
import com.lididimi.restaurant.utils.RestaurantUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BillServiceImplTest {

    @Mock
    private JwtFilter jwtFilter;

    @Mock
    private BillRepository billRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    @Spy
    private BillServiceImpl billService;

    private BillDTO billDTO;

    @BeforeEach
    public void setUp() {
        billDTO = new BillDTO();
        billDTO.setUuid("12345");
        billDTO.setName("John Doe");
        billDTO.setEmail("john.doe@example.com");
        billDTO.setContactNumber("0123456789");
        billDTO.setPaymentMethod(PaymentMethodNameEnum.DEBIT_CARD);
        billDTO.setTotal(BigDecimal.valueOf(100.00));
        billDTO.setProductDetails("[{\"name\": \"Pizza\", \"category\": \"Food\", \"quantity\": \"2\", \"price\": \"20.00\", \"total\": \"40.00\"}]");
        billDTO.setGenerate(false);
        billDTO.setCreatedBy("admin");
        billDTO.setCreatedDate(Instant.now());

        // Ensure the directory exists
        File directory = new File(RestaurantConstants.STORE_LOCATION);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    @Test
    public void testGenerateReport_Success() {
        // Mocking
        when(jwtFilter.currentUser()).thenReturn("admin");
        when(billRepository.save(any(BillEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        String result = billService.generateReport(billDTO);

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("\"uuid\": \"12345\""));

        // Verify the file creation
        String filePath = RestaurantConstants.STORE_LOCATION + "\\" + billDTO.getUuid() + ".pdf";
        File file = new File(filePath);
        System.out.println("File path: " + file.getAbsolutePath());  // Debugging info
        assertTrue(file.exists());

        // Clean up the created file
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testGenerateReport_Exception() throws Exception {
        // Mocking
        when(jwtFilter.currentUser()).thenReturn("admin");

        // Spy on the service and mock initializeDocument to throw an exception
        doThrow(new DocumentException()).when(billService).initializeDocument(anyString());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            billService.generateReport(billDTO);
        });
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
    }

    // Helper method to create mock PDF document
    private Document mockDocument(String fileName) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(RestaurantConstants.STORE_LOCATION + "\\" + fileName + ".pdf"));
        document.open();
        document.add(new Paragraph("Test PDF Content"));
        document.close();
        return document;
    }




    @Test
    public void testGenerateReport() throws Exception {
        BillDTO billDTO = new BillDTO();
        billDTO.setUuid("test-uuid");
        billDTO.setGenerate(false);

        when(jwtFilter.currentUser()).thenReturn("user");
        when(billRepository.save(any(BillEntity.class))).thenReturn(new BillEntity());

        String result = billService.generateReport(billDTO);

        assertNotNull(result);
        assertTrue(result.contains("test-uuid"));

        // Additional verifications can be added here
    }

    @Test
    public void testGetBillsAsAdmin() {
        BillEntity billEntity = new BillEntity();
        BillDTO billDTO = new BillDTO();
        billDTO.setName("Test");
        when(jwtFilter.isAdmin()).thenReturn(true);
        when(billRepository.getAllBills()).thenReturn(Optional.of(Collections.singletonList(billEntity)));
        when(modelMapper.map(any(BillEntity.class), eq(BillDTO.class))).thenReturn(billDTO);

        assertEquals(Collections.singletonList(billDTO), billService.getBills());
    }

    @Test
    public void testGetPdf() throws IOException {
        BillDTO billDTO = new BillDTO();
        billDTO.setUuid("test-uuid");
        String filePath = RestaurantConstants.STORE_LOCATION + "\\" + "test-uuid.pdf";

        when(RestaurantUtils.isFileExist(filePath)).thenReturn(false);
        when(billService.generateReport(billDTO)).thenReturn("{\"uuid\": \"test-uuid\"}");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Document document = new Document()) {
            PdfWriter.getInstance(document, outputStream);
            document.open();
            document.add(new com.itextpdf.text.Paragraph("Sample PDF"));
            document.close();
        }
        when(billService.getByteArray(filePath)).thenReturn(outputStream.toByteArray());

        byte[] pdfBytes = billService.getPdf(billDTO);
        assertNotNull(pdfBytes);
    }

    @Test
    public void testDelete() {
        BillEntity billEntity = new BillEntity();
        billEntity.setId(1L);

        when(billRepository.findById(1L)).thenReturn(Optional.of(billEntity));
        String response = billService.delete(1L);
        assertEquals(RestaurantConstants.PRODUCT_DELETE_SUCCESS, response);

        when(billRepository.findById(1L)).thenReturn(Optional.empty());
        response = billService.delete(1L);
        assertEquals(RestaurantConstants.CATEGORY_NOT_FOUND, response);
    }
}*/

