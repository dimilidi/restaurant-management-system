package com.lididimi.restaurant.restaurant_categories.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lididimi.restaurant.restaurant_categories.constants.CategoryConstants;
import com.lididimi.restaurant.restaurant_categories.model.dto.CategoryDTO;
import com.lididimi.restaurant.restaurant_categories.service.CategoryService;
import com.lididimi.restaurant.restaurant_categories.model.response.SuccessResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void testGetAllCategories() throws Exception {
        when(categoryService.getAllCategories(null)).thenReturn(Collections.singletonList(categoryDTO));

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Category"));
    }

    @Test
    public void testGetCount() throws Exception {
        when(categoryService.getCategoriesCount()).thenReturn(1L);

        mockMvc.perform(get("/categories/count"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L));
    }

    @Test
    public void testGetCategoryById() throws Exception {
        when(categoryService.getById(anyLong())).thenReturn(categoryDTO);

        mockMvc.perform(get("/categories/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Category"));
    }

    @Test
    public void testAddNewCategory() throws Exception {
        when(categoryService.addNewCategory(any(CategoryDTO.class))).thenReturn(CategoryConstants.CATEGORY_ADD_SUCCESS);

        mockMvc.perform(post("/categories/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(CategoryConstants.CATEGORY_ADD_SUCCESS));
    }

    @Test
    public void testUpdateCategory() throws Exception {
        when(categoryService.updateCategory(any(CategoryDTO.class))).thenReturn(CategoryConstants.CATEGORY_UPDATE_SUCCESS);

        mockMvc.perform(post("/categories/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(CategoryConstants.CATEGORY_UPDATE_SUCCESS));
    }

    @Test
    public void testDeleteCategory() throws Exception {
        when(categoryService.deleteCategory(anyLong())).thenReturn(CategoryConstants.CATEGORY_DELETE_SUCCESS);

        mockMvc.perform(delete("/categories/delete/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(CategoryConstants.CATEGORY_DELETE_SUCCESS));
    }
}
