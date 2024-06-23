package com.bonappetit.model.dto;

import com.bonappetit.model.enums.CategoryNameEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class AddRecipeDTO {
    @NotBlank(message = "Name must not be empty!")
    @Size(min = 2, max = 40, message = "Name must be between 2 and 40 characters!")
    private String name;

    @NotBlank(message = "Ingredients must not be empty!")
    @Size(min = 2, max = 150, message = "Ingredients must be between 2 and 80 characters!")
    private String ingredients;

    @NotNull(message = "Category must be selected!")
    private CategoryNameEnum category;

    public AddRecipeDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryNameEnum category) {
        this.category = category;
    }
}
