package com.lididimi.restaurant.restaurant_categories.controller;

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
    public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewCategory(@RequestBody @Valid CategoryDTO categoryDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        String responseMessage = categoryService.addNewCategory(categoryDTO);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), responseMessage, responseMessage);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllCategories(@RequestParam(required = false) String filterValue) {
        List<CategoryDTO> allCategories = categoryService.getAllCategories(filterValue);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", allCategories);
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
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), responseMessage, null);
        return ResponseEntity.ok(response);
    }
}
