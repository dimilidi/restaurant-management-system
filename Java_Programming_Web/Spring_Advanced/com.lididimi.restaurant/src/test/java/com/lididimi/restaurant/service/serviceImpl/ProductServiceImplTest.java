package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.exception.common.ObjectNotFoundException;
import com.lididimi.restaurant.exception.common.UnauthorizedAccessException;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.model.dto.CategoryDTO;
import com.lididimi.restaurant.model.dto.ProductAddDTO;
import com.lididimi.restaurant.model.dto.ProductDTO;
import com.lididimi.restaurant.model.entity.ProductEntity;
import com.lididimi.restaurant.model.enums.StatusNameEnum;
import com.lididimi.restaurant.repository.ProductRepository;
import com.lididimi.restaurant.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private JwtFilter jwtFilter;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private ProductServiceImpl productService;

    private ProductDTO productDTO;
    private ProductAddDTO productAddDTO;
    private ProductEntity productEntity;
    private ProductEntity productEntity1;
    private ProductEntity productEntity2;
    private List<ProductEntity> productEntities;
    private List<ProductDTO> productDTOs;
    private CategoryDTO categoryDTO;
    private Long productId;


    @BeforeEach
    public void setup() {
        productAddDTO = new ProductAddDTO();
        productAddDTO.setName("Pizza Margarita");
        productAddDTO.setCategoryId(1L);
        productAddDTO.setDescription("Delicious pizza with tomatoes and cheese");
        productAddDTO.setPrice(new BigDecimal("8.99"));

        productEntity = new ProductEntity();
        productEntity.setName(productAddDTO.getName());
        productEntity.setCategoryId(productAddDTO.getCategoryId());
        productEntity.setDescription(productAddDTO.getDescription());
        productEntity.setPrice(productAddDTO.getPrice());
        productEntity.setStatus(StatusNameEnum.ACTIVE);

        productEntity1 = new ProductEntity();
        productEntity1.setId(1L);
        productEntity1.setName("Pizza Pepperoni");
        productEntity1.setCategoryId(1L);
        productEntity1.setDescription("Spicy pepperoni pizza");
        productEntity1.setPrice(new BigDecimal("9.99"));
        productEntity1.setStatus(StatusNameEnum.ACTIVE);

        productEntity2 = new ProductEntity();
        productEntity2.setId(2L);
        productEntity2.setName("Pizza Pepperoni");
        productEntity2.setCategoryId(1L);
        productEntity2.setDescription("Spicy pepperoni pizza");
        productEntity2.setPrice(new BigDecimal("9.99"));
        productEntity2.setStatus(StatusNameEnum.ACTIVE);

        productEntities = List.of(productEntity, productEntity2);

        productDTO = new ProductDTO();
        productDTO.setId(productId);
        productDTO.setName("Pizza Margarita");
        productDTO.setCategoryName("Pizza");
        productDTO.setDescription("Delicious pizza with tomatoes and cheese");
        productDTO.setPrice(new BigDecimal("8.99"));
        productDTO.setStatus(StatusNameEnum.INACTIVE.name());

        ProductDTO productDTO2 = new ProductDTO();
        productDTO2.setId(productEntity2.getId());
        productDTO2.setName(productEntity2.getName());
        productDTO2.setCategoryId(productEntity2.getCategoryId());
        productDTO2.setDescription(productEntity2.getDescription());
        productDTO2.setPrice(productEntity2.getPrice());
        productDTO2.setCategoryName("Pizza");

        productDTOs = List.of(productDTO, productDTO2);

        categoryDTO = new CategoryDTO();
        categoryDTO.setId(productAddDTO.getCategoryId());
        categoryDTO.setName("Pizza");
    }

    @Test
    public void testAddNewProductSuccess() {
        when(jwtFilter.isAdmin()).thenReturn(true);
        when(categoryService.getCategoryById(productAddDTO.getCategoryId())).thenReturn(categoryDTO);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);

        String result = productService.addNewProduct(productAddDTO);

        assertEquals(RestaurantConstants.PRODUCT_ADD_SUCCESS, result);
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    public void testAddNewProductUnauthorized() {
        when(jwtFilter.isAdmin()).thenReturn(false);

        UnauthorizedAccessException exception = assertThrows(UnauthorizedAccessException.class, () -> {
            productService.addNewProduct(productAddDTO);
        });

        assertEquals(RestaurantConstants.UNAUTHORIZED_ACCESS, exception.getMessage());
        verify(productRepository, never()).save(any(ProductEntity.class));
    }

    @Test
    public void testGetAllProductsSuccess() {
        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(productEntity);

        when(productRepository.getAllProducts()).thenReturn(Optional.of(productEntities));
        when(categoryService.getCategoryById(anyLong())).thenReturn(categoryDTO);
        when(modelMapper.map(any(ProductEntity.class), eq(ProductDTO.class))).thenReturn(productDTO);

        List<ProductDTO> result = productService.getAllProducts();

        assertEquals(1, result.size());
        assertEquals(productDTO.getName(), result.get(0).getName());
        assertEquals(productDTO.getCategoryName(), result.get(0).getCategoryName());
        assertEquals(productDTO.getDescription(), result.get(0).getDescription());
        assertEquals(productDTO.getPrice(), result.get(0).getPrice());
        verify(productRepository, times(1)).getAllProducts();
        verify(categoryService, times(1)).getCategoryById(anyLong());
    }

    @Test
    public void testGetAllProductsEmptyList() {
        // Arrange
        List<ProductEntity> emptyProductEntities = new ArrayList<>();

        when(productRepository.getAllProducts()).thenReturn(Optional.of(emptyProductEntities));

        // Act
        List<ProductDTO> result = productService.getAllProducts();

        // Assert
        assertEquals(0, result.size(), "The result should be an empty list");
        verify(productRepository, times(1)).getAllProducts();
        verify(categoryService, never()).getCategoryById(anyLong());
        verify(modelMapper, never()).map(any(ProductEntity.class), eq(ProductDTO.class));
    }

    @Test
    public void testGetAllProductsNotFound() {
        when(productRepository.getAllProducts()).thenReturn(Optional.empty());

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
            productService.getAllProducts();
        });

        assertEquals(RestaurantConstants.NOT_FOUND, exception.getMessage());
        verify(productRepository, times(1)).getAllProducts();
        verify(categoryService, never()).getCategoryById(anyLong());
        verify(modelMapper, never()).map(any(ProductEntity.class), eq(ProductDTO.class));
    }

    @Test
    public void testUpdateProductSuccess() {
        // Arrange
        when(jwtFilter.isAdmin()).thenReturn(true);
        when(productRepository.findById(productDTO.getId())).thenReturn(Optional.of(productEntity));
        when(modelMapper.map(productDTO, ProductEntity.class)).thenReturn(productEntity);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);

        // Act
        String result = productService.updateProduct(productDTO);

        // Assert
        assertEquals(RestaurantConstants.PRODUCT_UPDATE_SUCCESS, result);
        verify(productRepository, times(1)).findById(productDTO.getId());
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    public void testUpdateProductUnauthorized() {
        // Arrange
        when(jwtFilter.isAdmin()).thenReturn(false);

        // Act & Assert
        UnauthorizedAccessException exception = assertThrows(UnauthorizedAccessException.class, () -> {
            productService.updateProduct(productDTO);
        });

        assertEquals(RestaurantConstants.UNAUTHORIZED_ACCESS, exception.getMessage());
        verify(productRepository, never()).findById(anyLong());
        verify(productRepository, never()).save(any(ProductEntity.class));
    }

    @Test
    public void testUpdateProductNotFound() {
        // Arrange
        when(jwtFilter.isAdmin()).thenReturn(true);
        when(productRepository.findById(productDTO.getId())).thenReturn(Optional.empty());

        // Act & Assert
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
            productService.updateProduct(productDTO);
        });

        assertEquals(RestaurantConstants.PRODUCT_NOT_FOUND, exception.getMessage());
        verify(productRepository, times(1)).findById(productDTO.getId());
        verify(productRepository, never()).save(any(ProductEntity.class));
    }

    @Test
    public void testDeleteProductSuccess() {
        // Arrange
        when(jwtFilter.isAdmin()).thenReturn(true);
        when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity));

        // Act
        String result = productService.deleteProduct(productId);

        // Assert
        assertEquals(RestaurantConstants.PRODUCT_DELETE_SUCCESS, result);
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    public void testDeleteProductUnauthorized() {
        // Arrange
        when(jwtFilter.isAdmin()).thenReturn(false);

        // Act & Assert
        UnauthorizedAccessException exception = assertThrows(UnauthorizedAccessException.class, () -> {
            productService.deleteProduct(productId);
        });

        assertEquals(RestaurantConstants.UNAUTHORIZED_ACCESS, exception.getMessage());
        verify(productRepository, never()).findById(anyLong());
        verify(productRepository, never()).deleteById(anyLong());
    }

    @Test
    public void testDeleteProductNotFound() {
        // Arrange
        when(jwtFilter.isAdmin()).thenReturn(true);
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
            productService.deleteProduct(productId);
        });

        assertEquals(RestaurantConstants.PRODUCT_NOT_FOUND, exception.getMessage());
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, never()).deleteById(anyLong());
    }

    @Test
    public void testUpdateStatusSuccess() {
        // Arrange
        when(jwtFilter.isAdmin()).thenReturn(true);
        when(productRepository.findById(productDTO.getId())).thenReturn(Optional.of(productEntity));

        // Act
        String result = productService.updateStatus(productDTO);

        // Assert
        assertEquals(RestaurantConstants.PRODUCT_UPDATE_SUCCESS, result);
        verify(productRepository, times(1)).findById(productDTO.getId());
        verify(productRepository, times(1)).updateProductStatus(StatusNameEnum.INACTIVE, productDTO.getId());
    }

    @Test
    public void testUpdateStatusUnauthorized() {
        // Arrange
        when(jwtFilter.isAdmin()).thenReturn(false);

        // Act & Assert
        UnauthorizedAccessException exception = assertThrows(UnauthorizedAccessException.class, () -> {
            productService.updateStatus(productDTO);
        });

        assertEquals(RestaurantConstants.UNAUTHORIZED_ACCESS, exception.getMessage());
        verify(productRepository, never()).findById(anyLong());
        verify(productRepository, never()).updateProductStatus(any(StatusNameEnum.class), anyLong());
    }

    @Test
    public void testUpdateStatusProductNotFound() {
        // Arrange
        when(jwtFilter.isAdmin()).thenReturn(true);
        when(productRepository.findById(productDTO.getId())).thenReturn(Optional.empty());

        // Act & Assert
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
            productService.updateStatus(productDTO);
        });

        assertEquals(RestaurantConstants.PRODUCT_NOT_FOUND, exception.getMessage());
        verify(productRepository, times(1)).findById(productDTO.getId());
        verify(productRepository, never()).updateProductStatus(any(StatusNameEnum.class), anyLong());
    }

    @Test
    public void testGetByCategorySuccess() {
        // Arrange
        Long categoryId = 1L;
        when(productRepository.getProductByCategory(categoryId)).thenReturn(Optional.of(productEntities));
        when(modelMapper.map(productEntities.get(0), ProductDTO.class)).thenReturn(productDTOs.get(0));
        when(modelMapper.map(productEntities.get(1), ProductDTO.class)).thenReturn(productDTOs.get(1));

        // Act
        List<ProductDTO> result = productService.getByCategory(categoryId);

        // Assert
        assertEquals(productDTOs, result);
        verify(productRepository, times(1)).getProductByCategory(categoryId);
        verify(modelMapper, times(2)).map(any(ProductEntity.class), eq(ProductDTO.class));
    }

    @Test
    public void testGetByCategoryNotFound() {
        // Arrange
        Long categoryId = 1L;
        when(productRepository.getProductByCategory(categoryId)).thenReturn(Optional.empty());

        // Act & Assert
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
            productService.getByCategory(categoryId);
        });

        assertEquals(RestaurantConstants.NOT_FOUND, exception.getMessage());
        verify(productRepository, times(1)).getProductByCategory(categoryId);
        verify(modelMapper, never()).map(any(ProductEntity.class), eq(ProductDTO.class));
    }

    @Test
    public void testGetProductByCategorySuccess() {
        // Arrange
        Long productId = 1L;
        when(productRepository.getProductById(productId)).thenReturn(Optional.of(productEntity));
        when(modelMapper.map(productEntity, ProductDTO.class)).thenReturn(productDTO);

        // Act
        ProductDTO result = productService.getProductByCategory(productId);

        // Assert
        assertEquals(productDTO, result);
        verify(productRepository, times(1)).getProductById(productId);
        verify(modelMapper, times(1)).map(productEntity, ProductDTO.class);
    }

    @Test
    public void testGetProductByCategoryNotFound() {
        // Arrange
        Long productId = 1L;
        when(productRepository.getProductById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
            productService.getProductByCategory(productId);
        });

        assertEquals(RestaurantConstants.PRODUCT_NOT_FOUND, exception.getMessage());
        verify(productRepository, times(1)).getProductById(productId);
        verify(modelMapper, never()).map(any(ProductEntity.class), eq(ProductDTO.class));
    }

    @Test
    public void testUpdateStatusByCategoryIdSuccess() {
        // Arrange
        Long categoryId = 1L;
        StatusNameEnum newStatus = StatusNameEnum.INACTIVE;
        List<ProductEntity> products = Arrays.asList(productEntity1, productEntity2);

        when(productRepository.getProductByCategory(categoryId)).thenReturn(Optional.of(products));

        // Act
        productService.updateStatusByCategoryId(categoryId, newStatus);

        // Assert
        for (ProductEntity product : products) {
            verify(productRepository).save(product);
            assertEquals(newStatus, product.getStatus());
        }
    }


    @Test
    public void testUpdateStatusByCategoryIdNoProducts() {
        // Arrange
        Long categoryId = 1L;
        StatusNameEnum newStatus = StatusNameEnum.INACTIVE;

        when(productRepository.getProductByCategory(categoryId)).thenReturn(Optional.empty());

        // Act
        productService.updateStatusByCategoryId(categoryId, newStatus);

        // Assert
        verify(productRepository, never()).save(any(ProductEntity.class));
    }

}
