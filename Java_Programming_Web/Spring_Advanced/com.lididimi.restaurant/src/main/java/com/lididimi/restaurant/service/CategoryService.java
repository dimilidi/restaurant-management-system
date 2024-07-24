package com.lididimi.restaurant.service;

import com.lididimi.restaurant.model.dto.CategoryDTO;
import java.util.List;
import java.util.Set;


public interface CategoryService {

    void addNewCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllCategories(String filterValue);

    List<CategoryDTO> getCategoriesWithActiveProducts();

    Object updateCategory(CategoryDTO categoryDTO);

    Long getCategoriesCount();

    CategoryDTO getCategoryById(Long id);

    List<CategoryDTO> getCategoriesByIds(List<Long> categoryIds);
}
