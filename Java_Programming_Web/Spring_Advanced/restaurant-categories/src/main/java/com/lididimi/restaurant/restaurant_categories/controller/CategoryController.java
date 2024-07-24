package com.lididimi.restaurant.restaurant_categories.controller;

import com.lididimi.restaurant.restaurant_categories.model.dto.CategoryDTO;
import com.lididimi.restaurant.restaurant_categories.model.entity.CategoryEntity;
import com.lididimi.restaurant.restaurant_categories.model.response.SuccessResponse;
import com.lididimi.restaurant.restaurant_categories.service.CategoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

@Slf4j
@RestController
@RequestMapping("/categories")
public class CategoryController {


    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    public ResponseEntity<?> addNewCategory(@RequestBody @Valid CategoryDTO categoryDTO, BindingResult bindingResult) {
        log.info("controller addNewCategory {}", categoryDTO);
   /*     if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }*/
  /*      String responseMessage = categoryService.addCategory(categoryDTO);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), responseMessage, responseMessage);*/
        categoryService.addNewCategory(categoryDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<?> getAllCategories(@RequestParam(required = false) String filterValue) {
        List<CategoryDTO> allCategories = categoryService.getAllCategories(filterValue);
        // SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", allCategories);
        return ResponseEntity.ok(allCategories);
    }

    @GetMapping("/count")
    public ResponseEntity<?> getCount() {
        long count = categoryService.getCategoriesCount();
       // SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", count);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id) {
        CategoryDTO categoryById = modelMapper.map(categoryService.getById(id), CategoryDTO.class); ;
       // SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", categoryById);
        return ResponseEntity.ok(categoryById);
    }

    @GetMapping("/cat")
    public ResponseEntity<?> getCategoriesByIds(List<Long> ids) {
        List<CategoryDTO> categories = categoryService.getCategoriesByIds(ids);
       // SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", categories);
        return ResponseEntity.ok(categories);
    }



    @PutMapping()
    public ResponseEntity<?> updateCategory(@RequestBody @Valid CategoryDTO categoryDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        String responseMessage = categoryService.updateCategory(categoryDTO);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), responseMessage, categoryDTO);
        return ResponseEntity.ok(response);
    }
}
