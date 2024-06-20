package com.bonappetit.controller;

import com.bonappetit.model.dto.RecipeDTO;
import com.bonappetit.model.entity.RecipeEntity;
import com.bonappetit.model.enums.CategoryNameEnum;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import com.bonappetit.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final UserService userService;
    private final RecipeService recipeService;
    private final CurrentUser currentUser;

    public HomeController(UserService userService, RecipeService recipeService, CurrentUser currentUser) {
        this.userService = userService;
        this.recipeService = recipeService;
        this.currentUser = currentUser;
    }

    @GetMapping("/")
    public String nonLoggedIndex() {
        if (userService.isLoggedIn()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    @Transactional
    public String home(Model model) {
        if (!userService.isLoggedIn()) {
            return "redirect:/";
        }

        Map<CategoryNameEnum, List<RecipeEntity>> recipesByCategory = recipeService.findAllRecipesByCategory();


        List<RecipeDTO> cocktails = recipesByCategory.get(CategoryNameEnum.COCKTAIL)
                .stream()
                .map(RecipeDTO::new)
                .toList();


        List<RecipeDTO> mainDishes = recipesByCategory.get(CategoryNameEnum.MAIN_DISH)
                .stream()
                .map(RecipeDTO::new)
                .toList();

        List<RecipeDTO> desserts = recipesByCategory.get(CategoryNameEnum.DESSERT)
                .stream()
                .map(RecipeDTO::new)
                .toList();

        List<RecipeDTO> favourites = userService.findFavourites(currentUser.id())
                .stream()
                .map(RecipeDTO::new)
                .toList();

        model.addAttribute("cocktailsData", cocktails);
        model.addAttribute("mainDishesData", mainDishes);
        model.addAttribute("dessertsData", desserts);
        model.addAttribute("favouritesData", favourites);

        return "home";
    }
}
