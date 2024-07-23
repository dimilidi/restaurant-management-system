package com.lididimi.restaurant.restaurant_categories.controller;

import com.lididimi.restaurant.restaurant_categories.model.dto.CategoryDTO;
import com.lididimi.restaurant.restaurant_categories.model.response.SuccessResponse;
import com.lididimi.restaurant.restaurant_categories.service.CategoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Slf4j
@RestController
@RequestMapping("/categories")
public class CategoryController {


    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
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
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", allCategories);
        return ResponseEntity.ok(allCategories);
    }

    @GetMapping("/count")
    public ResponseEntity<?> getCount() {
        long count = categoryService.getCategoriesCount();
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", count);
        return ResponseEntity.ok(count);
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
