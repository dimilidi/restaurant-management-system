package com.lididimi.restaurant.service;

import com.lididimi.restaurant.model.dto.ProductAddDTO;
import com.lididimi.restaurant.model.dto.ProductDTO;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    String addNewProduct(ProductAddDTO productAddDTO);

    List<ProductDTO> getAllProducts();

    String updateProduct(ProductDTO productDTO);

    String deleteProduct(Long id);

    String updateStatus(ProductDTO productDTO);

    List<ProductDTO> getByCategory(Long id);

    ProductDTO getProductByCategory(Long id);
}
