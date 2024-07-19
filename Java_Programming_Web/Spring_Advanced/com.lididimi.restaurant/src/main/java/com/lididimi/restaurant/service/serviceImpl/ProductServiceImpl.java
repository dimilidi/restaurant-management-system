package com.lididimi.restaurant.service.serviceImpl;

import com.google.common.io.Files;
import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.model.dto.ProductDTO;
import com.lididimi.restaurant.model.dto.ProductNewDTO;
import com.lididimi.restaurant.model.entity.CategoryEntity;
import com.lididimi.restaurant.model.entity.ProductEntity;
import com.lididimi.restaurant.model.enums.StatusNameEnum;
import com.lididimi.restaurant.repository.ProductRepository;
import com.lididimi.restaurant.service.ProductService;
import com.lididimi.restaurant.utils.RestaurantUtils;
import com.lididimi.restaurant.wrapper.ProductWrapper;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {
    private final JwtFilter jwtFilter;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(JwtFilter jwtFilter, ProductRepository productRepository, ModelMapper modelMapper) {
        this.jwtFilter = jwtFilter;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    private ProductEntity getProductFromMap(Map<String, String> requestMap, boolean isAdd) {
        CategoryEntity category = new CategoryEntity();
        category.setId(Long.parseLong(requestMap.get("categoryId")));

        ProductEntity product = new ProductEntity();

        if (isAdd) {
            product.setId(Long.parseLong(requestMap.get("id")));
        } else {
            product.setStatus(StatusNameEnum.ACTIVE);
        }
        product.setCategory(category);
        product.setName(requestMap.get("name"));
        product.setDescription(requestMap.get("description"));
        product.setPrice(new BigDecimal(requestMap.get("price")));
        return product;
    }

    @Override
    public ResponseEntity<String> addNewProduct(ProductDTO productDTO) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateProductMap(productDTO, false)) {
                    productRepository.save(getProductFromMap(productDTO, false));
                    return RestaurantUtils.getResponseEntity("{\"message\": \"Successfully added product.\"}", HttpStatus.CREATED);
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

    private ProductEntity getProductFromMap(ProductDTO productDTO, boolean isAdd) {
        CategoryEntity category = new CategoryEntity();
        category.setId(productDTO.getCategoryId());

        ProductEntity product = new ProductEntity();

        if (isAdd) {
            product.setId(productDTO.getId());
        } else {
            product.setStatus(StatusNameEnum.ACTIVE);
        }
        product.setName(productDTO.getName());
        product.setCategory(category);
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        return product;
    }


    @Override
    @Transactional
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        try {
            List<ProductEntity> productEntities = productRepository.getAllProducts();
            List<ProductDTO> productDTOs = productEntities.stream()
                    .map(entity -> modelMapper.map(entity, ProductDTO.class))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(productDTOs, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateProductMap(ProductDTO productDTO, boolean validateId) {
        if (productDTO.getName() != null && !productDTO.getName().isEmpty()) {
            if (productDTO.getId() != null && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }


    @Override
    public ResponseEntity<String> updateProduct(ProductDTO productDTO) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateProductMap(productDTO, true)) {
                    Optional<ProductEntity> optionalProduct = productRepository.findById(productDTO.getId());

                    if (optionalProduct.isPresent()) {
                        ProductEntity product = modelMapper.map(productDTO, ProductEntity.class);
                        product.setStatus(optionalProduct.get().getStatus());
                        productRepository.save(product);
                        return RestaurantUtils.getResponseEntity("{\"message\": \"Successfully updated product.\"}", HttpStatus.OK);
                    } else {
                        return RestaurantUtils.getResponseEntity("{\"message\": \"Product not found.\"}", HttpStatus.OK);
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
                    return RestaurantUtils.getResponseEntity("{\"message\": \"Successfully deleted product.\"}", HttpStatus.OK);
                }
                return RestaurantUtils.getResponseEntity("{\"message\": \"Product does not exist.\"}", HttpStatus.OK);

            } else {
                return RestaurantUtils.getResponseEntity(RestaurantConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateStatus(ProductDTO productDTO) {
        try {
            if (jwtFilter.isAdmin()) {
                Long id = productDTO.getId();
                Optional<ProductEntity> optionalProduct = productRepository.findById(id);
                if(optionalProduct.isPresent()) {
                    StatusNameEnum status = StatusNameEnum.valueOf(productDTO.getStatus());
                    productRepository.updateProductStatus(status, id);
                    return RestaurantUtils.getResponseEntity("{\"message\": \"Successfully updated product.\"}", HttpStatus.OK);
                } else {
                    return RestaurantUtils.getResponseEntity("{\"message\": \"Product does not exist.\"}", HttpStatus.OK);
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
    public ResponseEntity<List<ProductDTO>> getByCategory(Long id) {
        try {
            return new ResponseEntity<>(productRepository.getProductByCategory(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @Transactional()
    public ResponseEntity<ProductDTO> getProductByCategory(Long id) {
        try {
            return new ResponseEntity<>(productRepository.getProductById(id), HttpStatus.OK);
            // Initialize the lazy-loaded association
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ProductDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
