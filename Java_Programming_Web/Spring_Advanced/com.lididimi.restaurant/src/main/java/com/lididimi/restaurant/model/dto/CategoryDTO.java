package com.lididimi.restaurant.model.dto;

import com.lididimi.restaurant.model.validation.UniqueCategoryName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CategoryDTO {

    @NotNull
    @NotBlank(message = "Name is mandatory")
    @UniqueCategoryName
    private String name;
}
