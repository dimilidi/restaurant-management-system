package com.lididimi.restaurant.restaurant_categories.service;

import com.lididimi.restaurant.restaurant_categories.model.dto.CategoryDTO;
import com.lididimi.restaurant.restaurant_categories.model.entity.CategoryEntity;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    void addNewCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllCategories(String filterValue);

    String updateCategory(CategoryDTO categoryDTO);

    CategoryEntity getById(Long id);

    Long getCategoriesCount();

    List<CategoryDTO> getCategoriesByIds(List<Long> ids);
}
