package bg.softuni.shoppinglist.service;

import bg.softuni.shoppinglist.model.dto.ProductAddDTO;
import bg.softuni.shoppinglist.model.dto.ProductViewDTO;
import bg.softuni.shoppinglist.model.enums.CategoryNameEnum;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void add(ProductAddDTO productData);

    BigDecimal getTotalSum();

    List<ProductViewDTO> findAllProductsByCategoryName(CategoryNameEnum categoryNameEnum);
}
