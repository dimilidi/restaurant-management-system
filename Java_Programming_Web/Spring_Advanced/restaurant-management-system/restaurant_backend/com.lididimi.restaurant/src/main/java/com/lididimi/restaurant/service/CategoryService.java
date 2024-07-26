package com.lididimi.restaurant.service;

import com.lididimi.restaurant.model.dto.CategoryDTO;
import java.util.List;


public interface CategoryService {

    String addNewCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllCategories(String filterValue);

    List<CategoryDTO> getCategoriesWithActiveProducts();

    String updateCategory(CategoryDTO categoryDTO);

    Long getCategoriesCount();

    CategoryDTO getCategoryById(Long id);

    String deleteCategory(Long id);
}
