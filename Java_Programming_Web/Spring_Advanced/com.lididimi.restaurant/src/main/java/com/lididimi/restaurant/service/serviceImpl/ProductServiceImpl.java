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
import java.util.List;
import java.util.Map;


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

        if(isAdd) {
            product.setId(Long.parseLong(requestMap.get("productId")));
        } else {
            product.setStatus("true");
        }
        product.setCategory(category);
        product.setName(requestMap.get("name"));
        product.setDescription(requestMap.get("description"));
        product.setPrice(new BigDecimal(requestMap.get("price")));
        return product;
    }
}
