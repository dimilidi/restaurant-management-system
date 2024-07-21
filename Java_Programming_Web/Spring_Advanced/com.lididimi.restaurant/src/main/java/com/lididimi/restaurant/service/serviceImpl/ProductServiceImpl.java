package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.exception.common.ObjectNotFoundException;
import com.lididimi.restaurant.exception.product.ProductNotFoundException;
import com.lididimi.restaurant.exception.product.ProductsNotFoundException;
import com.lididimi.restaurant.exception.product.UnauthorizedAccessException;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.model.dto.ProductAddDTO;
import com.lididimi.restaurant.model.dto.ProductDTO;
import com.lididimi.restaurant.model.entity.CategoryEntity;
import com.lididimi.restaurant.model.entity.ProductEntity;
import com.lididimi.restaurant.model.enums.StatusNameEnum;
import com.lididimi.restaurant.repository.ProductRepository;
import com.lididimi.restaurant.service.ProductService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public String addNewProduct(ProductAddDTO productAddDTO) {
        if (!jwtFilter.isAdmin()) {
            throw new UnauthorizedAccessException(RestaurantConstants.UNAUTHORIZED_ACCESS);
        }

        productRepository.save(getProductFromMap(productAddDTO, false));
        return "Successfully added product.";
    }

    @Override
    @Transactional
    public List<ProductDTO> getAllProducts() {
        Optional<List<ProductEntity>> allProductsOpt = productRepository.getAllProducts();

        if (allProductsOpt.isEmpty()) {
            throw new ObjectNotFoundException("No products found.");
        }

        return allProductsOpt.get().stream()
                .map(entity -> modelMapper.map(entity, ProductDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public String updateProduct(ProductDTO productDTO) {
        if (!jwtFilter.isAdmin()) {
            throw new UnauthorizedAccessException("Unauthorized access.");
        }

        Optional<ProductEntity> optionalProduct = productRepository.findById(productDTO.getId());
        if (optionalProduct.isPresent()) {
            ProductEntity product = modelMapper.map(productDTO, ProductEntity.class);
            product.setStatus(optionalProduct.get().getStatus());
            productRepository.save(product);
            return "Successfully updated product.";
        } else {
            throw new ProductNotFoundException("Product not found.");
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
            return "Successfully deleted product.";
        } else {
            throw new ProductNotFoundException("Product does not exist.");
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
            return "Successfully updated product.";
        } else {
            throw new ProductNotFoundException("Product does not exist.");
        }
    }

    @Override
    @Transactional
    public List<ProductDTO> getByCategory(Long id) {
        Optional<List<ProductEntity>> productsOpt = productRepository.getProductByCategory(id);
        if (productsOpt.isEmpty()) {
            throw new ObjectNotFoundException("No products found.");
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
            throw new ProductNotFoundException("Product not found.");
        }
        ProductDTO productDTO = convertToDTO(productById.get());
        return productDTO;
    }

    private ProductEntity getProductFromMap(ProductAddDTO productAddDTO, boolean isAdd) {
        CategoryEntity category = new CategoryEntity();
        category.setId(productAddDTO.getCategoryId());

        ProductEntity product = new ProductEntity();

        if (isAdd) {
            product.setId(productAddDTO.getId());
        } else {
            product.setStatus(StatusNameEnum.ACTIVE);
        }
        product.setName(productAddDTO.getName());
        product.setCategory(category);
        product.setDescription(productAddDTO.getDescription());
        product.setPrice(productAddDTO.getPrice());
        return product;
    }

    private ProductDTO convertToDTO(ProductEntity productEntity) {
        return modelMapper.map(productEntity, ProductDTO.class);
    }
}
