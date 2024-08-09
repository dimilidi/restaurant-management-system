package com.lididimi.restaurant.service.serviceImpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.lididimi.restaurant.config.RetentionProperties;
import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.security.JwtFilter;
import com.lididimi.restaurant.model.dto.bill.BillDTO;
import com.lididimi.restaurant.model.entity.BillEntity;
import com.lididimi.restaurant.model.enums.PaymentMethodNameEnum;
import com.lididimi.restaurant.repository.BillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.*;
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

    @Mock
    private Cloudinary cloudinary;

    @Mock
    private Uploader uploader;

    @Mock
    private RetentionProperties retentionProperties;

    @Mock
    private Clock clock;

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
        billDTO.setCreatedDate(Instant.now(clock));

        lenient().when(retentionProperties.getPeriod()).thenReturn(Period.ofYears(1));
        lenient().when(cloudinary.uploader()).thenReturn(uploader);
    }


    @Test
    public void testCleanupOldBills() {
        // Arrange
        Instant fixedInstant = Instant.parse("2024-08-08T00:00:00Z");
        Clock fixedClock = Clock.fixed(fixedInstant, ZoneId.of("Europe/Berlin"));
        when(clock.instant()).thenReturn(fixedInstant);
        when(clock.getZone()).thenReturn(ZoneId.of("Europe/Berlin"));

        // Calculate expected cutoff date
        LocalDate now = LocalDate.now(fixedClock);
        LocalDate cutoffLocalDate = now.minus(Period.ofYears(1));
        Instant expectedCutoffDate = cutoffLocalDate.atStartOfDay(ZoneId.of(fixedClock.getZone().getId())).toInstant();

        // Act
        billService.cleanupOldBills();

        // Assert
        verify(billRepository).deleteBillsOlderThan(expectedCutoffDate);
    }


    @Test
    public void testGenerateReport_GenerateTrue() throws Exception {
        billDTO.setGenerate(true);
        billDTO.setUuid("test-uuid");

        String result = billService.generateReport(billDTO);

        assertEquals("{\"uuid\": \"test-uuid\"}", result);
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
        BillEntity billEntity = new BillEntity();

        when(billRepository.findById(id)).thenReturn(Optional.of(billEntity));
        doNothing().when(billRepository).deleteById(id);

        // Act
        String result = billService.delete(id);

        // Assert
        assertEquals(RestaurantConstants.PRODUCT_DELETE_SUCCESS, result);
        verify(billRepository, times(1)).findById(id);
        verify(billRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDelete_NotFound() {
        // Arrange
        Long id = 1L;

        when(billRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        String result = billService.delete(id);

        // Assert
        assertEquals(RestaurantConstants.CATEGORY_NOT_FOUND, result);
        verify(billRepository, times(1)).findById(id);
        verify(billRepository, never()).deleteById(id);
    }
}












