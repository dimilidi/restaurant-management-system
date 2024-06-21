package bg.softuni.shoppinglist.service.impl;

import bg.softuni.shoppinglist.model.dto.ProductViewDTO;
import bg.softuni.shoppinglist.model.enums.CategoryNameEnum;
import bg.softuni.shoppinglist.repository.ProductRepository;
import bg.softuni.shoppinglist.service.CategoryService;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ProductServiceImplImpl extends ProductServiceImpl {
    public ProductServiceImplImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper modelMapper) {
        super(productRepository, categoryService, modelMapper);
    }

    @Override
    public List<ProductViewDTO> findAllProductsByCategoryName(CategoryNameEnum categoryNameEnum) {
        return null;
    }
}
