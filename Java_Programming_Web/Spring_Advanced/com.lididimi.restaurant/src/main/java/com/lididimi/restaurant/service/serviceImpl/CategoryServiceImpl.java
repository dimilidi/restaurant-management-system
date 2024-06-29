package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.model.entity.CategoryEntity;
import com.lididimi.restaurant.repository.CategoryRepository;
import com.lididimi.restaurant.service.CategoryService;
import com.lididimi.restaurant.utils.RestaurantUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    private final JwtFilter jwtFilter;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(JwtFilter jwtFilter, CategoryRepository categoryRepository) {
        this.jwtFilter = jwtFilter;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateCategoryMap(requestMap, false)) {
                    categoryRepository.save(getCategoryFromMap(requestMap, false));
                    return RestaurantUtils.getResponseEntity("Category added successfully.", HttpStatus.CREATED);
                }
            } else {
                return RestaurantUtils.getResponseEntity(RestaurantConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    private boolean validateCategoryMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    private CategoryEntity getCategoryFromMap(Map<String, String> requestMap, Boolean isAdd) {
        CategoryEntity categoryEntity = new CategoryEntity();

        if (isAdd) {
            categoryEntity.setId(Long.parseLong(requestMap.get("id")));
        }
        categoryEntity.setName(requestMap.get("name"));
        return categoryEntity;
    }

    @Override
    public ResponseEntity<List<CategoryEntity>> getAllCategories(String filterValue) {
        try {
            if(filterValue != null && filterValue.equalsIgnoreCase("true")) {
                    log.info("Inside getAllCategories filter");
                return new ResponseEntity<List<CategoryEntity>>(categoryRepository.getAllCategories(), HttpStatus.OK);
            }
            return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
        try {
            if(jwtFilter.isAdmin()) {
                if (validateCategoryMap(requestMap, true)) {
                    Optional<CategoryEntity> categoryOptional = categoryRepository.findById(Long.parseLong(requestMap.get("id")));
                    if(categoryOptional.isPresent()) {
                        categoryRepository.save(getCategoryFromMap(requestMap, true));
                        return RestaurantUtils.getResponseEntity("Category updated successfully.", HttpStatus.OK);
                    } else {
                        return RestaurantUtils.getResponseEntity("Category does not exist", HttpStatus.OK);
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
