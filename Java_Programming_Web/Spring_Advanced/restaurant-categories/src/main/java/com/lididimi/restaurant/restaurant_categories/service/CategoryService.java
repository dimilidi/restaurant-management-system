package com.lididimi.restaurant.restaurant_categories.service;

import com.lididimi.restaurant.restaurant_categories.model.dto.CategoryDTO;
import com.lididimi.restaurant.restaurant_categories.model.entity.CategoryEntity;
import java.util.List;


public interface CategoryService {

    String addNewCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllCategories(String filterValue);

    String updateCategory(CategoryDTO categoryDTO);

    CategoryEntity getById(Long id);

    Long getCategoriesCount();

    String deleteCategory(Long id);

}
