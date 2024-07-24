package com.lididimi.restaurant.restaurant_categories.service;

import com.lididimi.restaurant.restaurant_categories.exception.AlreadyExistsException;
import com.lididimi.restaurant.restaurant_categories.exception.ObjectNotFoundException;
import com.lididimi.restaurant.restaurant_categories.model.dto.CategoryDTO;
import com.lididimi.restaurant.restaurant_categories.model.entity.CategoryEntity;
import com.lididimi.restaurant.restaurant_categories.repository.CategoryRepository;
import com.lididimi.restaurant.restaurant_categories.constants.CategoryConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    public String addNewCategory(CategoryDTO categoryDTO) {
        log.info("addNewCategory {}", categoryDTO);

        if(categoryRepository.existsByName(categoryDTO.getName())) {
            throw new AlreadyExistsException(CategoryConstants.ALREADY_EXISTS);
        }

        categoryRepository.save(getCategoryFromMap(categoryDTO, false));
        return CategoryConstants.CATEGORY_ADD_SUCCESS;
    }

    @Override
    public List<CategoryDTO> getAllCategories(String filterValue) {
       List<CategoryEntity> categories = categoryRepository.findAll();
               /*List<CategoryEntity> categories = (filterValue != null && filterValue.equalsIgnoreCase("true"))
                        ? categoryRepository.findByIdIn(ids)
                        : categoryRepository.findAll();*/

        return categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String updateCategory(CategoryDTO categoryDTO) {
        log.info("updateCategory {}", categoryDTO);

        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(categoryDTO.getId());
        if (categoryOptional.isEmpty()) {
            throw  new ObjectNotFoundException(CategoryConstants.CATEGORY_NOT_FOUND);
        }
        String newName = categoryDTO.getName();

        boolean existsByName = categoryRepository.existsByName(newName);
        boolean nameEqual= categoryOptional.get().getName().toLowerCase().equals(newName.toLowerCase());

        if (!nameEqual && existsByName) {
           throw new AlreadyExistsException(CategoryConstants.ALREADY_EXISTS);
        }
        categoryOptional.get().setName(categoryDTO.getName());

        categoryRepository.save(getCategoryFromMap(categoryDTO, true));
        return CategoryConstants.CATEGORY_UPDATE_SUCCESS;
    }

    @Override
    public CategoryEntity getById(Long id) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isEmpty()) {
            log.error("Category getById not found {}", id);
            throw new ObjectNotFoundException(CategoryConstants.CATEGORY_NOT_FOUND);
        }
        return categoryOptional.get();
    }

    @Override
    public Long getCategoriesCount() {
        return categoryRepository.count() < 0 ?  0 : categoryRepository.count();
    }

    @Override
    public List<CategoryDTO> getCategoriesByIds(List<Long> ids) {
        List<CategoryEntity> categories = categoryRepository.findAllById(ids);
        List list = List.of(1l, 7l);
       // List<CategoryEntity> categories = categoryRepository.findByIdIn(list);
        log.info("getCategoriesByIds {}", categories);
    var result = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());

    log.info("getCategoriesByIds {}", result);

    return result;
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
