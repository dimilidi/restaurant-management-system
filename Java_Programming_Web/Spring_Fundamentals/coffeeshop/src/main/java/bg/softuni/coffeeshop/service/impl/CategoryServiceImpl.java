package bg.softuni.coffeeshop.service.impl;

import bg.softuni.coffeeshop.model.entity.Category;
import bg.softuni.coffeeshop.model.entity.CategoryNameEnum;
import bg.softuni.coffeeshop.repository.CategoryRepository;
import bg.softuni.coffeeshop.service.CategoryService;
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
        if (categoryRepository.count() != 0) {
            return;
        }

        List<Category> categories = Arrays.stream(CategoryNameEnum.values())
                .map(Category::new)
                .collect(Collectors.toList());

        categoryRepository.saveAll(categories);
    }

    @Override
    public Category findByCategoryNameEnum(CategoryNameEnum category) {
        return categoryRepository.findByName(category).orElse(null);
    }
}
