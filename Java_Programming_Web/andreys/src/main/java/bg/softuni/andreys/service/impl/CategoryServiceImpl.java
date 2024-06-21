package bg.softuni.andreys.service.impl;

import bg.softuni.andreys.model.entity.CategoryEntity;
import bg.softuni.andreys.model.enums.CategotyNameEnum;
import bg.softuni.andreys.repository.CategoryRepository;
import bg.softuni.andreys.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;

        this.modelMapper = modelMapper;
    }

    @Override
    public void initCategories() {
        if(categoryRepository.count() > 0) {
            return;
        }

        List<CategoryEntity> categoryEntities = Arrays.stream(CategotyNameEnum.values())
                .map(c -> new CategoryEntity(c, "Description for " + c.name()))
                .toList();

        categoryRepository.saveAll(categoryEntities);
    }
}
