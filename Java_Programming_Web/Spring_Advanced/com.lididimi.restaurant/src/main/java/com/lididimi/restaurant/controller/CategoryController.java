package com.lididimi.restaurant.controller;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.model.dto.CategoryDTO;
import com.lididimi.restaurant.model.response.SuccessResponse;
import com.lididimi.restaurant.service.CategoryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
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
  /*      String responseMessage = categoryService.addNewCategory(categoryDTO);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), responseMessage, responseMessage);*/
        categoryService.addNewCategory(categoryDTO);
        return ResponseEntity.ok().build();
    }

/*    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO createdCategory = categoryService.addCategory(categoryDTO);
        return ResponseEntity.ok(createdCategory);
    }*/

    @GetMapping("/get")
    public ResponseEntity<?> getAllCategories(@RequestParam(required = false) String filterValue) {
        List<CategoryDTO> allCategories = categoryService.getAllCategories(filterValue);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", allCategories);
        return ResponseEntity.ok(response);
    }

  @GetMapping("/getFiltered")
    public ResponseEntity<?> getAllCategoriesWithActiveProducts() {
        List<CategoryDTO> allCategories = categoryService.getCategoriesWithActiveProducts();
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", allCategories);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id) {
        CategoryDTO categoryById = categoryService.getCategoryById(id);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", categoryById);
        return ResponseEntity.ok(response);
    }

    /*@GetMapping("/cat")
    public ResponseEntity<?> getCategoriesByIds(List<Long> ids) {
        List<CategoryDTO> categoriesByIds = categoryService.getCategoriesByIds(ids);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", categoriesByIds);
        return ResponseEntity.ok(response);
    }*/

    @GetMapping("/count")
    public ResponseEntity<?> getCategoriesCount() {
        long count = categoryService.getCategoriesCount();
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", count);
        return ResponseEntity.ok(count);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestBody @Valid CategoryDTO categoryDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        categoryService.updateCategory(categoryDTO);
        String responseMessage = RestaurantConstants.CATEGORY_UPDATE_SUCCESS;
                SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), responseMessage, null);
        return ResponseEntity.ok(response);
     /*   categoryService.updateCategory(categoryDTO);
        return ResponseEntity.ok().build();*/
    }
}
