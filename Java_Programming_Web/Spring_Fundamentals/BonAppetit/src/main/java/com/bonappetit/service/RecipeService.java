package com.bonappetit.service;

import com.bonappetit.model.dto.AddRecipeDTO;
import com.bonappetit.model.entity.CategoryEntity;
import com.bonappetit.model.entity.RecipeEntity;
import com.bonappetit.model.entity.UserEntity;
import com.bonappetit.model.enums.CategoryNameEnum;
import com.bonappetit.repo.CategoryRepository;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.util.CurrentUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeService {
    private final UserService userService;
    private final CurrentUser currentUser;
    private final CategoryService categoryService;
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public RecipeService(UserService userService, CurrentUser currentUser, CategoryService categoryService, RecipeRepository recipeRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.userService = userService;
        this.currentUser = currentUser;
        this.categoryService = categoryService;
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public boolean add(AddRecipeDTO data) {

        if(!currentUser.isLoggedIn()) {
            return false;
        }

        RecipeEntity recipe = new RecipeEntity();

        recipe.setName(data.getName());
        recipe.setIngredients(data.getIngredients());
        CategoryEntity category = categoryService.findByCategoryNameEnum(data.getCategory());
        recipe.setCategory(category);

        final UserEntity addedBy = userService.findByUsername(currentUser.username());
        recipe.setAddedBy(addedBy);

        recipeRepository.save(recipe);

        return true;

    }

    public Map<CategoryNameEnum, List<RecipeEntity>> findAllRecipesByCategory() {
        Map<CategoryNameEnum, List<RecipeEntity>> result = new HashMap<>();

        List<CategoryEntity> allCategories = categoryRepository.findAll();


        for (CategoryEntity category : allCategories) {
            List<RecipeEntity> recipes = recipeRepository.findAllByCategory(category);

            result.put(category.getName(), recipes);
        }
        return result;
    }

    @Transactional
    public void addToFavourites(Long userId, Long recipeId) {
        Optional<UserEntity> userOpt = userRepository.findById(userId);

        if (userOpt.isEmpty()) {
            return;
        }

        Optional<RecipeEntity> recipeOpt = recipeRepository.findById(recipeId);

        if(recipeOpt.isEmpty()) {
            return;
        }

        UserEntity user = userOpt.get();
        RecipeEntity recipeEntity = recipeOpt.get();

        if(user.getFavouriteRecipes().contains(recipeEntity)) {
            return;
        }

        userOpt.get().addFavourite(recipeEntity);

        userRepository.save(userOpt.get());
    }
}
