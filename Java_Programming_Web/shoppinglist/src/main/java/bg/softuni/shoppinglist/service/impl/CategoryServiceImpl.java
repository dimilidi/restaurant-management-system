package bg.softuni.shoppinglist.service.impl;

import bg.softuni.shoppinglist.model.entity.CategoryEntity;
import bg.softuni.shoppinglist.model.enums.CategoryNameEnum;
import bg.softuni.shoppinglist.repository.CategoryRepository;
import bg.softuni.shoppinglist.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if(categoryRepository.count() > 0) {
            return;
        }

        List<CategoryEntity> categories = Arrays.stream(CategoryNameEnum.values())
                .map( categoryName -> new CategoryEntity(categoryName,"Description for " + categoryName.name()))
                .collect(Collectors.toList());

        categoryRepository.saveAll(categories);
    }
}
