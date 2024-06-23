package com.bonappetit.controller;

import com.bonappetit.model.dto.AddRecipeDTO;
import com.bonappetit.model.enums.CategoryNameEnum;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import com.bonappetit.util.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;
    private final UserService userService;
    private final CurrentUser currentUser;

    public RecipeController(RecipeService recipeService, UserService userService, CurrentUser currentUser) {
        this.recipeService = recipeService;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("addRecipeData")
    public AddRecipeDTO initAddRecipeDTO() {
        return new AddRecipeDTO();
    }

    @ModelAttribute("categories")
    public CategoryNameEnum[] categories() {
        return CategoryNameEnum.values();
    }

    @GetMapping("/add")
    public String viewAddRecipe() {
        if (!userService.isLoggedIn()) {
            return "redirect:/";
        }

        return "recipe-add";
    }

    @PostMapping("/add")
    public String doAddRecipe(@Valid AddRecipeDTO data,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        if (!userService.isLoggedIn()) {
            return "redirect:/";
        }

        if(bindingResult.hasErrors() ) {
            redirectAttributes.addFlashAttribute("addRecipeData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addRecipeData", bindingResult);

            return "redirect:/add";
        }

        boolean isSuccess = recipeService.add(data);

        if (!isSuccess) {
            // show generic error ?
            return "redirect:/add";
        }

        return "redirect:/home";
    }

    @PostMapping("/add-to-favourites/{recipeId}")
    public String addToFavourites(@PathVariable("recipeId") Long recipeId) {
        if (!userService.isLoggedIn()) {
            return "redirect:/";
        }

        recipeService.addToFavourites(currentUser.id(), recipeId);

        return "redirect:/home";
    }

}
