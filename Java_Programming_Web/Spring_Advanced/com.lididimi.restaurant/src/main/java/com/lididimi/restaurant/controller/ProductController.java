package com.lididimi.restaurant.controller;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.model.entity.ProductEntity;
import com.lididimi.restaurant.repository.ProductRepository;
import com.lididimi.restaurant.service.ProductService;
import com.lididimi.restaurant.service.serviceImpl.ProductServiceImpl;
import com.lididimi.restaurant.utils.RestaurantUtils;
import com.lididimi.restaurant.wrapper.ProductWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    public ProductController(ProductRepository productRepository, ProductServiceImpl productServiceImpl, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewProduct(@RequestBody Map<String, String> requestMap) {
        try {
            return productService.addNewProduct(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/get")
    public ResponseEntity<List<ProductWrapper>> getAllProducts() {
        try {
            return productService.getAllProducts();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody(required = true) Map<String, String> requestMap) {
        try {
            return productService.updateProduct(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            return productService.deleteProduct(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping ("/updateStatus")
    public ResponseEntity<String> updateStatus(@RequestBody Map<String, String> requestMap) {
        try {
            return productService.updateStatus(requestMap);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getByCategory/{id}")
    public ResponseEntity<List<ProductWrapper>> getByCategory(@PathVariable Long id) {
        try {
            return productService.getByCategory(id);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ProductWrapper> getById(@PathVariable Long id) {
        try {
            return productService.getProductByCategory(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ProductWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
