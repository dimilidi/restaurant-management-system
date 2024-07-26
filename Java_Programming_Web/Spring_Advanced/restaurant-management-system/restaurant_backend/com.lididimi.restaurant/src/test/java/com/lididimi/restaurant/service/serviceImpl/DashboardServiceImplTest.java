package com.lididimi.restaurant.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.lididimi.restaurant.repository.BillRepository;
import com.lididimi.restaurant.repository.ProductRepository;
import com.lididimi.restaurant.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

public class DashboardServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BillRepository billRepository;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private DashboardServiceImpl dashboardServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCount() {
        // Arrange
        when(categoryService.getCategoriesCount()).thenReturn(5L); // Return Long
        when(productRepository.count()).thenReturn(100L); // Return Long
        when(billRepository.count()).thenReturn(50L); // Return Long

        // Act
        Map<String, Object> countMap = dashboardServiceImpl.getCount();

        // Assert
        assertNotNull(countMap);
        assertEquals(5L, countMap.get("category")); // Compare Long with Long
        assertEquals(100L, countMap.get("product")); // Compare Long with Long
        assertEquals(50L, countMap.get("bill")); // Compare Long with Long
    }
}
