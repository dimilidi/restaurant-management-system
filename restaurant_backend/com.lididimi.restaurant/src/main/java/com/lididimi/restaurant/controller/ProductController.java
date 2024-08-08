package com.lididimi.restaurant.controller;

import com.lididimi.restaurant.model.dto.product.ProductAddDTO;
import com.lididimi.restaurant.model.dto.product.ProductDTO;
import com.lididimi.restaurant.model.dto.product.ProductOrderDTO;
import com.lididimi.restaurant.model.response.ErrorResponse;
import com.lididimi.restaurant.repository.ProductRepository;
import com.lididimi.restaurant.model.response.SuccessResponse;
import com.lididimi.restaurant.service.BillService;
import com.lididimi.restaurant.service.MonitoringService;
import com.lididimi.restaurant.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Products", description = "The controller responsible for product management.")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;
    private final BillService billService;
    private final MonitoringService monitoringService;


    public ProductController(
            ProductRepository productRepository,
            ProductService productService,
            BillService billService,
            MonitoringService monitoringService
    ) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.billService = billService;
        this.monitoringService = monitoringService;
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
        monitoringService.increaseProductSearches();

        List<ProductDTO> allProducts = productService.getAllProducts();
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", allProducts);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getByCategory/{id}")
    public ResponseEntity<?> getByCategory(@PathVariable Long id) {
        List<ProductDTO> byCategory = productService.getByCategory(id);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", byCategory);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get product by category ID",
            security = @SecurityRequirement(name = "bearer-key"))
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Product filtered by category", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class))),
                    @ApiResponse(responseCode = "404", description = "If the offer was not found",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "401", description = "If user is unauthorized",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @GetMapping("/getById/{id}")
    @Transactional
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ProductOrderDTO product = productService.getProductByCategory(id);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", product);
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

    @PatchMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestBody ProductDTO productDTO) {
        String responseMessage = productService.updateStatus(productDTO);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), responseMessage, null);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        String responseMessage = productService.deleteProduct(id);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), responseMessage, null);
        return ResponseEntity.ok(response);
    }
}

