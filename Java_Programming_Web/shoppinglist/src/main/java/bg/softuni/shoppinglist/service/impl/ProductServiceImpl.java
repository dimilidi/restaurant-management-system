package bg.softuni.shoppinglist.service.impl;

import bg.softuni.shoppinglist.model.dto.ProductAddDTO;
import bg.softuni.shoppinglist.model.entity.ProductEntity;
import bg.softuni.shoppinglist.model.enums.CategoryNameEnum;
import bg.softuni.shoppinglist.repository.ProductRepository;
import bg.softuni.shoppinglist.service.CategoryService;
import bg.softuni.shoppinglist.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(ProductAddDTO productData) {

        ProductEntity product = modelMapper.map(productData, ProductEntity.class);
        product.setCategory(categoryService.findByName(productData.getCategory()));

        productRepository.save(product);
    }

    @Override
    public BigDecimal getTotalSum() {
        return productRepository.findTotalProductSum();
    }
}
