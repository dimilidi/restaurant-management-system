package com.lididimi.restaurant.service.serviceImpl;

import com.google.common.io.Files;
import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.model.dto.CategoryDTO;
import com.lididimi.restaurant.model.entity.CategoryEntity;
import com.lididimi.restaurant.repository.CategoryRepository;
import com.lididimi.restaurant.service.CategoryService;
import com.lididimi.restaurant.utils.RestaurantUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<String> addNewCategory(CategoryDTO categoryDTO) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateCategoryMap(categoryDTO, false)) {
                    categoryRepository.save(getCategoryFromMap(categoryDTO, false));
                    return RestaurantUtils.getResponseEntity("{\"message\": \"Category added successfully.\"}", HttpStatus.CREATED);
                }
            } else {
                return RestaurantUtils.getResponseEntity(RestaurantConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    private boolean validateCategoryMap(CategoryDTO categoryDTO, boolean validateId) {
        if (categoryDTO.getName() != null && !categoryDTO.getName().isEmpty()) {
            if (categoryDTO.getId() != null && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    private CategoryEntity getCategoryFromMap(CategoryDTO categoryDTO, Boolean isAdd) {
        CategoryEntity categoryEntity = new CategoryEntity();

        if (isAdd) {
            categoryEntity.setId(categoryDTO.getId());
        }
        categoryEntity.setName(categoryDTO.getName());
        return categoryEntity;
    }

    public ResponseEntity<List<CategoryDTO>> getAllCategories(String filterValue) {
        try {
            List<CategoryEntity> categories;
            if (filterValue != null && filterValue.equalsIgnoreCase("true")) {
                categories = categoryRepository.getAllCategories();
            } else {
                categories = categoryRepository.findAll();
            }

            List<CategoryDTO> categoryDTOs = categories.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(categoryDTOs, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private CategoryDTO convertToDTO(CategoryEntity categoryEntity) {
        return modelMapper.map(categoryEntity, CategoryDTO.class);
    }


    @Override
    public ResponseEntity<String> updateCategory(CategoryDTO categoryDTO) {
        try {
            if(jwtFilter.isAdmin()) {
                if (validateCategoryMap(categoryDTO, true)) {
                    Optional<CategoryEntity> categoryOptional = categoryRepository.findById(categoryDTO.getId());
                    if(categoryOptional.isPresent()) {
                        categoryRepository.save(getCategoryFromMap(categoryDTO, true));
                        return RestaurantUtils.getResponseEntity("{\"message\": \"Category updated successfully.\"}", HttpStatus.OK);
                    } else {
                        return RestaurantUtils.getResponseEntity("{\"message\": \"Category does not exist.\"}", HttpStatus.OK);
                    }
                }
                return RestaurantUtils.getResponseEntity(RestaurantConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else {
                return RestaurantUtils.getResponseEntity(RestaurantConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
