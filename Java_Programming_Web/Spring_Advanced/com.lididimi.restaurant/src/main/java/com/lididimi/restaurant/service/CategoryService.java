package com.lididimi.restaurant.service;

import com.lididimi.restaurant.model.dto.CategoryDTO;
import com.lididimi.restaurant.model.entity.CategoryEntity;
import com.lididimi.restaurant.model.response.SuccessResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CategoryService {


    void addNewCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllCategories(String filterValue);

    Object updateCategory(CategoryDTO categoryDTO);

    long getCategoriesCount();
}
