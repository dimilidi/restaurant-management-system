package com.lididimi.restaurant.restaurant_categories.service;

import com.lididimi.restaurant.restaurant_categories.exception.ObjectNotFoundException;
import com.lididimi.restaurant.restaurant_categories.model.dto.CategoryDTO;
import com.lididimi.restaurant.restaurant_categories.model.entity.CategoryEntity;
import com.lididimi.restaurant.restaurant_categories.repository.CategoryRepository;
import com.lididimi.restaurant.restaurant_categories.constants.CategoryConstants;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CatServiceImpl implements CategoryService {
//private final JwtFilter jwtFilter;



    private final CategoryRepository categoryRepository;
     private final ModelMapper modelMapper;

    public CatServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addNewCategory(CategoryDTO categoryDTO) {
        log.info("addNewCategory {}", categoryDTO);
/*
  if (!jwtFilter.isAdmin()) {
            throw new UnauthorizedAccessException(CategoryConstants.UNAUTHORIZED_ACCESS);
        }
*/
        categoryRepository.save(getCategoryFromMap(categoryDTO, false));
  /*      return CategoryConstants.CATEGORY_ADD_SUCCESS;*/
    }

    @Override
    public List<CategoryDTO> getAllCategories(String filterValue) {
        List<CategoryEntity> categories = categoryRepository.findAll();
              /* /List<CategoryEntity> categories = (filterValue != null && filterValue.equalsIgnoreCase("true"))
                        ? categoryRepository.getAllCategories().orElseGet(ArrayList::new)
                        : categoryRepository.findAll();*/

        return categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String updateCategory(CategoryDTO categoryDTO) {
        log.info("updateCategory {}", categoryDTO);
/*   if(!jwtFilter.isAdmin()) {
            log.info("Updating category - isAdmin {}", jwtFilter.isAdmin());
            throw new UnauthorizedAccessException(RestaurantConstants.UNAUTHORIZED_ACCESS);
        }*/

        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(categoryDTO.getId());
        if(categoryOptional.isEmpty()) {
            throw  new ObjectNotFoundException(CategoryConstants.CATEGORY_NOT_FOUND);
        }

        boolean nameExists = categoryOptional.get().getName().equals(categoryDTO.getName());
        if (nameExists) {
            throw new IllegalArgumentException("Category name already exists");
        }

        categoryRepository.save(getCategoryFromMap(categoryDTO, true));
        return CategoryConstants.CATEGORY_UPDATE_SUCCESS;
    }

    @Override
    public CategoryEntity getById(Long id) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isEmpty()) {
            throw new ObjectNotFoundException(CategoryConstants.CATEGORY_NOT_FOUND);
        }
        return categoryOptional.get();
    }

    @Override
    public long getCategoriesCount() {
        return categoryRepository.count() < 0 ?  0 : categoryRepository.count();
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
