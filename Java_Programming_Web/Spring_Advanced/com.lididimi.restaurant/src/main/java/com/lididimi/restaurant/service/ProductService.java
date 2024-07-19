package com.lididimi.restaurant.service;

import com.lididimi.restaurant.model.dto.ProductDTO;
import com.lididimi.restaurant.wrapper.ProductWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ResponseEntity<String> addNewProduct(ProductDTO productDTO);

   ResponseEntity<List<ProductDTO>> getAllProducts();

    ResponseEntity<String> updateProduct(ProductDTO productDTO);

    ResponseEntity<String> deleteProduct(Long id);

    ResponseEntity<String> updateStatus(ProductDTO productDTO);

    ResponseEntity<List<ProductDTO>> getByCategory(Long id);

    ResponseEntity<ProductDTO> getProductByCategory(Long id);


}
