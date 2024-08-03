/*
package com.lididimi.restaurant.service.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.exception.common.ObjectNotFoundException;
import com.lididimi.restaurant.exception.common.SomethingWentWrongException;
import com.lididimi.restaurant.exception.common.UnauthorizedAccessException;
import com.lididimi.restaurant.exception.user.AlreadyExistsException;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.model.dto.category.CategoryDTO;
import com.lididimi.restaurant.model.entity.ProductEntity;
import com.lididimi.restaurant.repository.ProductRepository;
import com.lididimi.restaurant.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockRestServiceServer
@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @Mock
    private JwtFilter jwtFilter;

    @Mock
    private RestClient categoryRestClient;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllCategoriesSuccess() {
        // Setup mock response
        List<CategoryDTO> categories = Collections.singletonList(new CategoryDTO(1L, "Category1"));
        RestClient.RequestHeadersUriSpec<?> requestHeadersUriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec<?> requestHeadersSpec = mock(RestClient.RequestHeadersSpec.class);
        when(categoryRestClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/categories")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.accept(MediaType.APPLICATION_JSON)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.body((Class<List<CategoryDTO>>) any())).thenReturn(categories);

        List<CategoryDTO> result = categoryService.getAllCategories("");

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Category1", result.get(0).getName());
    }

    @Test
    void getCategoriesWithActiveProductsSuccess() {
        // Setup mock data
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategoryId(1L);

        when(productRepository.findAllActiveProducts()).thenReturn(Collections.singletonList(productEntity));

        // Mock category retrieval
        RestClient.RequestHeadersUriSpec<?> requestHeadersUriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec<?> requestHeadersSpec = mock(RestClient.RequestHeadersSpec.class);
        when(categoryRestClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/categories/1")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.accept(MediaType.APPLICATION_JSON)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.body(CategoryDTO.class)).thenReturn(new CategoryDTO(1L, "Category1"));

        List<CategoryDTO> result = categoryService.getCategoriesWithActiveProducts();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Category1", result.get(0).getName());
    }

    @Test
    void getCategoriesCountSuccess() {
        // Setup mock response
        RestClient.RequestHeadersUriSpec<?> requestHeadersUriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec<?> requestHeadersSpec = mock(RestClient.RequestHeadersSpec.class);
        when(categoryRestClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/categories/count")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.accept(MediaType.APPLICATION_JSON)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.body(Long.class)).thenReturn(5L);

        Long count = categoryService.getCategoriesCount();

        assertNotNull(count);
        assertEquals(5L, count);
    }

    @Test
    void getCategoryByIdSuccess() {
        // Setup mock response
        RestClient.RequestHeadersUriSpec<?> requestHeadersUriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec<?> requestHeadersSpec = mock(RestClient.RequestHeadersSpec.class);
        when(categoryRestClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/categories/1")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.accept(MediaType.APPLICATION_JSON)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.body(CategoryDTO.class)).thenReturn(new CategoryDTO(1L, "Category1"));

        CategoryDTO result = categoryService.getCategoryById(1L);

        assertNotNull(result);
        assertEquals("Category1", result.getName());
    }

    @Test
    void addNewCategorySuccess() {
        // Setup mock response
        CategoryDTO categoryDTO = new CategoryDTO(1L, "Category1");
        when(jwtFilter.isAdmin()).thenReturn(true);
        RestClient.RequestHeadersSpec<?> requestHeadersSpec = mock(RestClient.RequestHeadersSpec.class);
        RestClient.RequestHeadersUriSpec<?> requestHeadersUriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        when(categoryRestClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/categories/add")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.body(categoryDTO)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.body(String.class)).thenReturn("Category added");

        String response = categoryService.addNewCategory(categoryDTO);

        assertEquals("Category added", response);
    }

    @Test
    void updateCategorySuccess() {
        // Setup mock response
        CategoryDTO categoryDTO = new CategoryDTO(1L, "Category1");
        when(jwtFilter.isAdmin()).thenReturn(true);
        RestClient.RequestHeadersSpec<?> requestHeadersSpec = mock(RestClient.RequestHeadersSpec.class);
        RestClient.RequestHeadersUriSpec<?> requestHeadersUriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        when(categoryRestClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/categories/update")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.body(categoryDTO)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.body(String.class)).thenReturn("Category updated");

        String response = categoryService.updateCategory(categoryDTO);

        assertEquals("Category updated", response);
    }

    @Test
    void deleteCategorySuccess() {
        // Setup mock response
        when(jwtFilter.isAdmin()).thenReturn(true);
        when(productRepository.getProductByCategory(1L)).thenReturn(Optional.empty());
        RestClient.RequestHeadersUriSpec<?> requestHeadersUriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec<?> requestHeadersSpec = mock(RestClient.RequestHeadersSpec.class);
        when(categoryRestClient.delete()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/categories/delete/{id}", 1L)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.body(String.class)).thenReturn("Category deleted");

        String response = categoryService.deleteCategory(1L);

        assertEquals("Category deleted", response);
    }

    @Test
    void addNewCategoryUnauthorized() {
        // Setup mock response
        CategoryDTO categoryDTO = new CategoryDTO(1L, "Category1");
        when(jwtFilter.isAdmin()).thenReturn(false);

        UnauthorizedAccessException exception = assertThrows(UnauthorizedAccessException.class, () -> {
            categoryService.addNewCategory(categoryDTO);
        });

        assertEquals(RestaurantConstants.UNAUTHORIZED_ACCESS, exception.getMessage());
    }

    @Test
    void handleAddNewCategoryNotFound() {
        // Setup mock response
        CategoryDTO categoryDTO = new CategoryDTO(1L, "Category1");
        when(jwtFilter.isAdmin()).thenReturn(true);
        RestClient.RequestHeadersUriSpec<?> requestHeadersUriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec<?> requestHeadersSpec = mock(RestClient.RequestHeadersSpec.class);
        when(categoryRestClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/categories/add")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.body(categoryDTO)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.body(String.class)).thenThrow(new RestClientResponseException(NOT_FOUND.value(), "Not Found", null, null, null, null));

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
            categoryService.addNewCategory(categoryDTO);
        });

        assertEquals(RestaurantConstants.CATEGORY_NOT_FOUND, exception.getMessage());
    }

    @Test
    void handleUpdateCategoryBadRequest() {
        // Setup mock response
        CategoryDTO categoryDTO = new CategoryDTO(1L, "Category1");
        when(jwtFilter.isAdmin()).thenReturn(true);
        RestClient.RequestHeadersUriSpec<?> requestHeadersUriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec<?> requestHeadersSpec = mock(RestClient.RequestHeadersSpec.class);
        when(categoryRestClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/categories/update")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.body(categoryDTO)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.body(String.class)).thenThrow(new RestClientResponseException(BAD_REQUEST.value(), "Bad Request", null, null, null, null));

        AlreadyExistsException exception = assertThrows(AlreadyExistsException.class, () -> {
            categoryService.updateCategory(categoryDTO);
        });

        assertEquals(RestaurantConstants.ALREADY_EXISTS, exception.getMessage());
    }

    @Test
    void handleDeleteCategoryServerError() {
        // Setup mock response
        when(jwtFilter.isAdmin()).thenReturn(true);
        when(productRepository.getProductByCategory(1L)).thenReturn(Optional.empty());
        RestClient.RequestHeadersUriSpec<?> requestHeadersUriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec<?> requestHeadersSpec = mock(RestClient.RequestHeadersSpec.class);
        when(categoryRestClient.delete()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/categories/delete/{id}", 1L)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.body(String.class)).thenThrow(new RestClientResponseException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", null, null, null, null));

        SomethingWentWrongException exception = assertThrows(SomethingWentWrongException.class, () -> {
            categoryService.deleteCategory(1L);
        });

        assertEquals(RestaurantConstants.SOMETHING_WENT_WRONG, exception.getMessage());
    }
}
*/
