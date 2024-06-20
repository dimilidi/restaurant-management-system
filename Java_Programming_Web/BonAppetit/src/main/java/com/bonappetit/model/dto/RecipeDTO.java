package com.bonappetit.model.dto;

import com.bonappetit.model.entity.RecipeEntity;
import com.bonappetit.model.enums.CategoryNameEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class RecipeDTO {

    private Long id;

    private String name;

    private String ingredients;


    public RecipeDTO() {
    }

    public RecipeDTO(RecipeEntity recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.ingredients = recipe.getIngredients();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
