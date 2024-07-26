package com.lididimi.restaurant.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDTO {

    private Long id;

    @NotNull(message = "Name is mandatory")
    @Size(min = 2, max = 20, message = "Category name must have min 2 and max 20 characters")
    private String name;
}
