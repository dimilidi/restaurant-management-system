package com.lididimi.restaurant.service;

import com.lididimi.restaurant.model.entity.CategoryEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CategoryService {


    ResponseEntity<String> addNewCategory(Map<String, String> requestMap);

    ResponseEntity<List<CategoryEntity>> getAllCategories(String filterValue);

    ResponseEntity<String> updateCategory(Map<String, String> requestMap);
}
