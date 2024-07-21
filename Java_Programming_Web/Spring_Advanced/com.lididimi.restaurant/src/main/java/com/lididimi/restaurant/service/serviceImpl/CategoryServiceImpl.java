package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.exception.common.ObjectNotFoundException;
import com.lididimi.restaurant.exception.common.UnauthorizedAccessException;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.model.dto.CategoryDTO;
import com.lididimi.restaurant.model.entity.CategoryEntity;
import com.lididimi.restaurant.repository.CategoryRepository;
import com.lididimi.restaurant.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    private final JwtFilter jwtFilter;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(JwtFilter jwtFilter, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.jwtFilter = jwtFilter;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String addNewCategory(CategoryDTO categoryDTO) {
        if (!jwtFilter.isAdmin()) {
            throw new UnauthorizedAccessException(RestaurantConstants.UNAUTHORIZED_ACCESS);
        }
        categoryRepository.save(getCategoryFromMap(categoryDTO, false));
        return "Category added successfully.";
    }

    @Override
    public List<CategoryDTO> getAllCategories(String filterValue) {
        List<CategoryEntity> categories =
                (filterValue != null && filterValue.equalsIgnoreCase("true"))
                        ? categoryRepository.getAllCategories().orElseGet(ArrayList::new)
                        : categoryRepository.findAll();

        return categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String updateCategory(CategoryDTO categoryDTO) {
        if(!jwtFilter.isAdmin()) {
            log.info("Updating category - isAdmin {}", jwtFilter.isAdmin());
            throw new UnauthorizedAccessException(RestaurantConstants.UNAUTHORIZED_ACCESS);
        }

        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(categoryDTO.getId());
        if(categoryOptional.isPresent()) {
            categoryRepository.save(getCategoryFromMap(categoryDTO, true));
            return "Category updated successfully.";
        } else {
            throw  new ObjectNotFoundException("Category not found.");
        }
    }

    private CategoryEntity getCategoryFromMap(CategoryDTO categoryDTO, Boolean isAdd) {
        CategoryEntity categoryEntity = new CategoryEntity();

        if (isAdd) {
            categoryEntity.setId(categoryDTO.getId());
        }
        categoryEntity.setName(categoryDTO.getName());
        return categoryEntity;
    }

    private CategoryDTO convertToDTO(CategoryEntity categoryEntity) {
        return modelMapper.map(categoryEntity, CategoryDTO.class);
    }
}
