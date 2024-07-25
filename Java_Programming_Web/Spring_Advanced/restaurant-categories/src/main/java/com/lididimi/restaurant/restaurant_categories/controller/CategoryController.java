package com.lididimi.restaurant.restaurant_categories.controller;

import com.lididimi.restaurant.restaurant_categories.model.dto.CategoryDTO;
import com.lididimi.restaurant.restaurant_categories.model.response.SuccessResponse;
import com.lididimi.restaurant.restaurant_categories.service.CategoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @GetMapping()
    public ResponseEntity<?> getAllCategories(@RequestParam(required = false) String filterValue) {
        List<CategoryDTO> allCategories = categoryService.getAllCategories(filterValue);
        return ResponseEntity.ok(allCategories);
    }

    @GetMapping("/count")
    public ResponseEntity<?> getCount() {
        long count = categoryService.getCategoriesCount();
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", count);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id) {
        CategoryDTO categoryById = modelMapper.map(categoryService.getById(id), CategoryDTO.class); ;
        return ResponseEntity.ok(categoryById);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewCategory(@RequestBody @Valid CategoryDTO categoryDTO, BindingResult bindingResult) {
        log.info("controller addNewCategory {}", categoryDTO);
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        String responseMessage = categoryService.addNewCategory(categoryDTO);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), responseMessage, null);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
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


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        String responseMessage = categoryService.deleteCategory(id);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), responseMessage, null);
        return ResponseEntity.ok(response.getMessage());
    }
}
