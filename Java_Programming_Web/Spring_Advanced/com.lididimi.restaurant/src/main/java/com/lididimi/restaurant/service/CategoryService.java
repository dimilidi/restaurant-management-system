package com.lididimi.restaurant.service;

import com.lididimi.restaurant.model.dto.CategoryDTO;
import com.lididimi.restaurant.model.entity.CategoryEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CategoryService {


    ResponseEntity<String> addNewCategory(CategoryDTO categoryDTO);

    ResponseEntity<List<CategoryDTO>> getAllCategories(String filterValue);

    ResponseEntity<String> updateCategory(CategoryDTO categoryDTO);
}
