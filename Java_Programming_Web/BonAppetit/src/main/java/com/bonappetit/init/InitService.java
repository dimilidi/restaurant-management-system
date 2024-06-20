package com.bonappetit.init;

import com.bonappetit.model.entity.CategoryEntity;
import com.bonappetit.model.enums.CategoryNameEnum;
import com.bonappetit.repo.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class InitService implements CommandLineRunner {

    private final Map<CategoryNameEnum, String> descriptions = Map.of(
            CategoryNameEnum.MAIN_DISH, "Heart of the meal, substantial and satisfying; main dish delights taste buds.",
            CategoryNameEnum.COCKTAIL, "Sip of sophistication, cocktails blend flavors, creating a spirited symphony in every glass.",
            CategoryNameEnum.DESSERT, "Sweet finale, indulgent and delightful; dessert crowns the dining experience with joy."
    );

    private final CategoryRepository categoryRepository;

    public InitService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = this.categoryRepository.count();

        if(count > 0) {
            return;
        }

        List<CategoryEntity> toInsert =  Arrays.stream(CategoryNameEnum.values())
                .map(c -> new CategoryEntity(c, descriptions.get(c)))
                .toList();

        this.categoryRepository.saveAll(toInsert);
    }
}
