package com.lididimi.restaurant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lididimi.restaurant.model.dto.CategoryDTO;
import com.lididimi.restaurant.model.response.SuccessResponse;
import com.lididimi.restaurant.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoryService categoryService;

    private CategoryDTO categoryDTO;

    @BeforeEach
    public void setUp() {
        categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("Test Category");
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetAllCategories() throws Exception {
        List<CategoryDTO> categories = Arrays.asList(categoryDTO);
        SuccessResponse response = new SuccessResponse(200, "", categories);

        when(categoryService.getAllCategories(null)).thenReturn(categories);

        mockMvc.perform(MockMvcRequestBuilders.get("/categories/get"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(""))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value("Test Category"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetCategoryById() throws Exception {
        SuccessResponse response = new SuccessResponse(200, "", categoryDTO);

        when(categoryService.getCategoryById(1L)).thenReturn(categoryDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/categories/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(""))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("Test Category"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetCategoriesCount() throws Exception {
        long count = 10L;
        SuccessResponse response = new SuccessResponse(200, "", count);

        when(categoryService.getCategoriesCount()).thenReturn(count);

        mockMvc.perform(MockMvcRequestBuilders.get("/categories/count"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(""))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(10));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetAllCategoriesWithActiveProducts() throws Exception {
        List<CategoryDTO> categories = Arrays.asList(categoryDTO);
        SuccessResponse response = new SuccessResponse(200, "", categories);

        when(categoryService.getCategoriesWithActiveProducts()).thenReturn(categories);

        mockMvc.perform(MockMvcRequestBuilders.get("/categories/filter"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(""))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value("Test Category"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAddNewCategory_Success() throws Exception {
        String responseMessage = "Category added successfully";
        SuccessResponse response = new SuccessResponse(200, responseMessage, null);

        when(categoryService.addNewCategory(any(CategoryDTO.class))).thenReturn(responseMessage);

        mockMvc.perform(MockMvcRequestBuilders.post("/categories/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(responseMessage));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAddNewCategory_ValidationError() throws Exception {
        categoryDTO.setName(""); // Invalid name

        Map<String, String> errors = new HashMap<>();
        errors.put("name", "Category name must have min 2 and max 20 characters");

        mockMvc.perform(MockMvcRequestBuilders.post("/categories/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Category name must have min 2 and max 20 characters"));
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateCategory_Success() throws Exception {
        String responseMessage = "Category updated successfully";
        SuccessResponse response = new SuccessResponse(200, responseMessage, null);

        when(categoryService.updateCategory(any(CategoryDTO.class))).thenReturn(responseMessage);

        mockMvc.perform(MockMvcRequestBuilders.post("/categories/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(responseMessage));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateCategory_ValidationError() throws Exception {
        categoryDTO.setName(""); // Invalid name

        Map<String, String> errors = new HashMap<>();
        errors.put("name", "Category name must have min 2 and max 20 characters");

        mockMvc.perform(MockMvcRequestBuilders.post("/categories/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Category name must have min 2 and max 20 characters"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteCategory_Success() throws Exception {
        String responseMessage = "Category deleted successfully";
        SuccessResponse response = new SuccessResponse(200, responseMessage, null);

        when(categoryService.deleteCategory(1L)).thenReturn(responseMessage);

        mockMvc.perform(MockMvcRequestBuilders.delete("/categories/delete/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(responseMessage));
    }
}
