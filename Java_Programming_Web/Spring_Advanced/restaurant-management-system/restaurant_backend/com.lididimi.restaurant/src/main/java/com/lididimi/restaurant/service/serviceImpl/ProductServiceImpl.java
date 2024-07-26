package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.exception.common.ObjectNotFoundException;
import com.lididimi.restaurant.exception.common.UnauthorizedAccessException;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.model.dto.CategoryDTO;
import com.lididimi.restaurant.model.dto.ProductAddDTO;
import com.lididimi.restaurant.model.dto.ProductDTO;
import com.lididimi.restaurant.model.entity.ProductEntity;
import com.lididimi.restaurant.model.enums.StatusNameEnum;
import com.lididimi.restaurant.repository.ProductRepository;
import com.lididimi.restaurant.service.CategoryService;
import com.lididimi.restaurant.service.ProductService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final JwtFilter jwtFilter;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public ProductServiceImpl(
            JwtFilter jwtFilter,
            ProductRepository productRepository,
            ModelMapper modelMapper,
            CategoryService categoryService
    ) {
        this.jwtFilter = jwtFilter;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public String addNewProduct(ProductAddDTO productAddDTO) {
        log.info("addNewProduct called {}", productAddDTO);
        if (!jwtFilter.isAdmin()) {
            throw new UnauthorizedAccessException(RestaurantConstants.UNAUTHORIZED_ACCESS);
        }
        ProductEntity productFromMap = getProductFromMap(productAddDTO, false);

        log.info("Add product- Category id {} ",productFromMap.getCategoryId());

        productRepository.save(productFromMap);
        return RestaurantConstants.PRODUCT_ADD_SUCCESS;
    }

    @Override
    @Transactional
    public List<ProductDTO> getAllProducts() {
        Optional<List<ProductEntity>> allProductsOpt = productRepository.getAllProducts();

        if (allProductsOpt.isEmpty()) {
            throw new ObjectNotFoundException(RestaurantConstants.NOT_FOUND);
        }

        return allProductsOpt.get().stream()
                .map(entity -> {
                    CategoryDTO category = categoryService.getCategoryById(entity.getCategoryId());
                    ProductDTO productDTO = modelMapper.map(entity, ProductDTO.class);
                    productDTO.setCategoryName(category.getName());
                    return productDTO;
                })
                .collect(Collectors.toList());
    }


    @Override
    public String updateProduct(ProductDTO productDTO) {
        if (!jwtFilter.isAdmin()) {
            throw new UnauthorizedAccessException(RestaurantConstants.UNAUTHORIZED_ACCESS);
        }

        Optional<ProductEntity> optionalProduct = productRepository.findById(productDTO.getId());
        if (optionalProduct.isPresent()) {
            ProductEntity product = modelMapper.map(productDTO, ProductEntity.class);
            product.setStatus(optionalProduct.get().getStatus());
            productRepository.save(product);
            return RestaurantConstants.PRODUCT_UPDATE_SUCCESS;
        } else {
            throw new ObjectNotFoundException(RestaurantConstants.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public String updateStatus(ProductDTO productDTO) {
        if (!jwtFilter.isAdmin()) {
            throw new UnauthorizedAccessException(RestaurantConstants.UNAUTHORIZED_ACCESS);
        }

        Long id = productDTO.getId();
        Optional<ProductEntity> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            StatusNameEnum status = StatusNameEnum.valueOf(productDTO.getStatus());
            productRepository.updateProductStatus(status, id);
            return RestaurantConstants.PRODUCT_UPDATE_SUCCESS;
        } else {
            throw new ObjectNotFoundException(RestaurantConstants.PRODUCT_NOT_FOUND);
        }
    }


    @Override
    public String deleteProduct(Long id) {
        if (!jwtFilter.isAdmin()) {
            throw new UnauthorizedAccessException(RestaurantConstants.UNAUTHORIZED_ACCESS);
        }

        Optional<ProductEntity> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            return RestaurantConstants.PRODUCT_DELETE_SUCCESS;
        } else {
            throw new ObjectNotFoundException(RestaurantConstants.PRODUCT_NOT_FOUND);
        }
    }



    @Override
    @Transactional
    public List<ProductDTO> getByCategory(Long id) {
        Optional<List<ProductEntity>> productsOpt = productRepository.getProductByCategory(id);
        if (productsOpt.isEmpty()) {
            throw new ObjectNotFoundException(RestaurantConstants.NOT_FOUND);
        }

        List<ProductDTO> products = productsOpt.get().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return products;
    }


    @Override
    @Transactional
    public ProductDTO getProductByCategory(Long id) {
        Optional<ProductEntity> productById = productRepository.getProductById(id);
        if (productById.isEmpty()) {
            throw new ObjectNotFoundException(RestaurantConstants.PRODUCT_NOT_FOUND);
        }
        ProductDTO productDTO = convertToDTO(productById.get());
        return productDTO;
    }


    @Override
    @Transactional
    public void updateStatusByCategoryId(Long categoryId, StatusNameEnum status) {
        log.info("Updating products to status {} for category ID {}", status, categoryId);
        Optional<List<ProductEntity>> optionalProducts = productRepository.getProductByCategory(categoryId);

        if (optionalProducts.isPresent()) {
            List<ProductEntity> products = optionalProducts.get();
            for (ProductEntity product : products) {
                product.setStatus(status);
                productRepository.save(product);
            }
        } else {
            log.info("No products found for category ID {}", categoryId);
        }
    }


    private ProductEntity getProductFromMap(ProductAddDTO productAddDTO, boolean isAdd) {
        Long categoryId = productAddDTO.getCategoryId();

        CategoryDTO category = categoryService.getCategoryById(categoryId);
        category.setId(categoryId);

        ProductEntity product = new ProductEntity();

        if (isAdd) {
            product.setId(productAddDTO.getId());
        } else {
            product.setStatus(StatusNameEnum.ACTIVE);
        }
        product.setName(productAddDTO.getName());
        product.setCategoryId(category.getId());
        product.setDescription(productAddDTO.getDescription());
        product.setPrice(productAddDTO.getPrice());
        return product;
    }

    private ProductDTO convertToDTO(ProductEntity productEntity) {
        return modelMapper.map(productEntity, ProductDTO.class);
    }
}
