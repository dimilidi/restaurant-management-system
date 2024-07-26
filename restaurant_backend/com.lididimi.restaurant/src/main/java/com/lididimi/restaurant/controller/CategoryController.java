package com.lididimi.restaurant.controller;

import com.lididimi.restaurant.model.dto.CategoryDTO;
import com.lididimi.restaurant.model.response.SuccessResponse;
import com.lididimi.restaurant.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllCategories(@RequestParam(required = false) String filterValue) {
        List<CategoryDTO> allCategories = categoryService.getAllCategories(filterValue);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", allCategories);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id) {
        CategoryDTO categoryById = categoryService.getCategoryById(id);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", categoryById);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/count")
    public ResponseEntity<?> getCategoriesCount() {
        long count = categoryService.getCategoriesCount();
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", count);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getAllCategoriesWithActiveProducts() {
        List<CategoryDTO> allCategories = categoryService.getCategoriesWithActiveProducts();
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", allCategories);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewCategory(@RequestBody @Valid CategoryDTO categoryDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        String responseMessage = categoryService.addNewCategory(categoryDTO);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), responseMessage, null);
        return ResponseEntity.ok(response.getMessage());
    }


    @PostMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestBody @Valid CategoryDTO categoryDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        String responseMessage = categoryService.updateCategory(categoryDTO);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), responseMessage, null);
        return ResponseEntity.ok(response.getMessage());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        String responseMessage = categoryService.deleteCategory(id);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), responseMessage, null);
        return ResponseEntity.ok(response);
    }
}
