package bg.softuni.shoppinglist.service;

import bg.softuni.shoppinglist.model.entity.CategoryEntity;
import bg.softuni.shoppinglist.model.enums.CategoryNameEnum;
import org.springframework.stereotype.Service;


public interface CategoryService {
    void initCategories();

    CategoryEntity findByName(CategoryNameEnum categoryNameEnum);
}
