package com.lididimi.restaurant.controller;

import com.lididimi.restaurant.model.dto.ProductAddDTO;
import com.lididimi.restaurant.model.dto.ProductDTO;
import com.lididimi.restaurant.repository.ProductRepository;
import com.lididimi.restaurant.model.response.SuccessResponse;
import com.lididimi.restaurant.service.ProductService;
import com.lididimi.restaurant.service.serviceImpl.BillServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;
    private final BillServiceImpl billService;

    public ProductController(ProductRepository productRepository, ProductService productService, BillServiceImpl billService) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.billService = billService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewProduct(@RequestBody @Valid ProductAddDTO productAddDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), productService.addNewProduct(productAddDTO), null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllProducts() {
        List<ProductDTO> allProducts = productService.getAllProducts();
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", allProducts);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody @Valid ProductDTO productDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        String responseMessage = productService.updateProduct(productDTO);

        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), responseMessage, null);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        String responseMessage = productService.deleteProduct(id);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), responseMessage, null);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestBody ProductDTO productDTO) {
        String responseMessage = productService.updateStatus(productDTO);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), responseMessage, null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getByCategory/{id}")
    public ResponseEntity<?> getByCategory(@PathVariable Long id) {
        List<ProductDTO> byCategory = productService.getByCategory(id);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", byCategory);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getById/{id}")
    @Transactional
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ProductDTO products = productService.getProductByCategory(id);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", products);
        return ResponseEntity.ok(response);
    }
}

