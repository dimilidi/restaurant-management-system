package com.lididimi.restaurant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.model.dto.product.ProductAddDTO;
import com.lididimi.restaurant.model.dto.product.ProductDTO;
import com.lididimi.restaurant.model.dto.product.ProductOrderDTO;
import com.lididimi.restaurant.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "smtp_username=dummyUsername",
        "smtp_password=dummyPassword",
})
@AutoConfigureMockMvc
public class ProductControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    private ProductAddDTO productAddDTO;
    private ProductDTO productDTO;
    private ProductOrderDTO productOrderDTO;
    private List<ProductDTO> products;

    @BeforeEach
    public void setUp() {
        productAddDTO = new ProductAddDTO();
        productAddDTO.setName("Product1");
        productAddDTO.setCategoryId(1L);
        productAddDTO.setDescription("A test product");
        productAddDTO.setPrice(BigDecimal.valueOf(10.99));

        productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Product1");
        productDTO.setCategoryId(1L);
        productDTO.setDescription("A test product");
        productDTO.setPrice(BigDecimal.valueOf(10.99));
        productDTO.setStatus("ACTIVE");

        productOrderDTO = new ProductOrderDTO();
        productOrderDTO.setId(1L);
        productOrderDTO.setName("Product1");
        productOrderDTO.setCategoryId(1L);
        productOrderDTO.setDescription("A test product");
        productOrderDTO.setPrice(BigDecimal.valueOf(10.99));
        productOrderDTO.setStatus("ACTIVE");

        products = Arrays.asList(productDTO);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAddNewProduct() throws Exception {
        String successMessage = RestaurantConstants.PRODUCT_ADD_SUCCESS;

        when(productService.addNewProduct(any(ProductAddDTO.class))).thenReturn(successMessage);

        mockMvc.perform(post("/products/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productAddDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(successMessage));
    }


    @Test
    @WithMockUser(roles = "USER")
    public void testGetAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products/get")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", org.hamcrest.Matchers.hasSize(products.size())))
                .andExpect(jsonPath("$.data[0].name").value("Product1"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateProduct() throws Exception {
        when(productService.updateProduct(any(ProductDTO.class))).thenReturn(RestaurantConstants.PRODUCT_UPDATE_SUCCESS);

        mockMvc.perform(post("/products/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(RestaurantConstants.PRODUCT_UPDATE_SUCCESS));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteProduct() throws Exception {
        when(productService.deleteProduct(anyLong())).thenReturn(RestaurantConstants.PRODUCT_DELETE_SUCCESS);

        mockMvc.perform(delete("/products/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(RestaurantConstants.PRODUCT_DELETE_SUCCESS));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateStatus() throws Exception {
        when(productService.updateStatus(any(ProductDTO.class))).thenReturn(RestaurantConstants.PRODUCT_UPDATE_SUCCESS);

        mockMvc.perform(patch("/products/updateStatus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(RestaurantConstants.PRODUCT_UPDATE_SUCCESS));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetByCategory() throws Exception {
        when(productService.getByCategory(anyLong())).thenReturn(products);

        mockMvc.perform(get("/products/getByCategory/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", org.hamcrest.Matchers.hasSize(products.size())))
                .andExpect(jsonPath("$.data[0].name").value("Product1"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetById() throws Exception {
        when(productService.getProductByCategory(anyLong())).thenReturn(productOrderDTO);

        mockMvc.perform(get("/products/getById/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Product1"));
    }
}

