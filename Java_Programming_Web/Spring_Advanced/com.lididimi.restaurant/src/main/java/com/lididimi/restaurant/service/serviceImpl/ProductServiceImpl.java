package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.model.entity.CategoryEntity;
import com.lididimi.restaurant.model.entity.ProductEntity;
import com.lididimi.restaurant.repository.ProductRepository;
import com.lididimi.restaurant.service.ProductService;
import com.lididimi.restaurant.utils.RestaurantUtils;
import com.lididimi.restaurant.wrapper.ProductWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {
    private final JwtFilter jwtFilter;
    private final ProductRepository productRepository;

    public ProductServiceImpl(JwtFilter jwtFilter, ProductRepository productRepository) {
        this.jwtFilter = jwtFilter;
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateProductMap(requestMap, false)) {
                    productRepository.save(getProductFromMap(requestMap, false));
                    return RestaurantUtils.getResponseEntity("Successfully added product.", HttpStatus.CREATED);
                }
                return RestaurantUtils.getResponseEntity(RestaurantConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else {
                return RestaurantUtils.getResponseEntity(RestaurantConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getAllProducts() {
        try {
            return new ResponseEntity<>(productRepository.getAllProducts(), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateProductMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }


    private ProductEntity getProductFromMap(Map<String, String> requestMap, boolean isAdd) {
        CategoryEntity category = new CategoryEntity();
        category.setId(Long.parseLong(requestMap.get("categoryId")));

        ProductEntity product = new ProductEntity();

        if (isAdd) {
            product.setId(Long.parseLong(requestMap.get("id")));
        } else {
            product.setStatus("true");
        }
        product.setCategory(category);
        product.setName(requestMap.get("name"));
        product.setDescription(requestMap.get("description"));
        product.setPrice(new BigDecimal(requestMap.get("price")));
        return product;
    }


    @Override
    public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateProductMap(requestMap, true)) {
                    Optional<ProductEntity> optionalProduct = productRepository.findById(Long.parseLong(requestMap.get("id")));

                    if (optionalProduct.isPresent()) {
                        ProductEntity product = getProductFromMap(requestMap, true);
                        product.setStatus(optionalProduct.get().getStatus());
                        productRepository.save(product);
                        return RestaurantUtils.getResponseEntity("Successfully updated product.", HttpStatus.OK);
                    } else {
                        return RestaurantUtils.getResponseEntity("Product not found.", HttpStatus.OK);
                    }
                }
                return RestaurantUtils.getResponseEntity(RestaurantConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else {
                return RestaurantUtils.getResponseEntity(RestaurantConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteProduct(Long id) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<ProductEntity> optionalProduct = productRepository.findById(id);
                if (optionalProduct.isPresent()) {
                    productRepository.deleteById(id);
                    return RestaurantUtils.getResponseEntity("Successfully deleted product.", HttpStatus.OK);
                }
                    return RestaurantUtils.getResponseEntity("Product does not exist.", HttpStatus.OK);

            } else {
                return RestaurantUtils.getResponseEntity(RestaurantConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                Long id = Long.parseLong(requestMap.get("id"));
                Optional<ProductEntity> optionalProduct = productRepository.findById(id);
                if(optionalProduct.isPresent()) {
                    productRepository.updateProductStatus(requestMap.get("status"), id);
                    return RestaurantUtils.getResponseEntity("Successfully updated product status.", HttpStatus.OK);
                } else {
                   return RestaurantUtils.getResponseEntity("Product does not exist.", HttpStatus.OK);
                }
            } else {
                return RestaurantUtils.getResponseEntity(RestaurantConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getByCategory(Long id) {
        try {
            return new ResponseEntity<>(productRepository.getProductByCategory(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ProductWrapper> getProductByCategory(Long id) {
        try {
            return new ResponseEntity<>(productRepository.getProductById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ProductWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
