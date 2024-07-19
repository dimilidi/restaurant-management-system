package com.lididimi.restaurant.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ProductNewDTO {
    private Long id;

    @NotNull
    @NotBlank(message = "Product name is mandatory")
    private String name;

    @NotNull(message = "Category id is mandatory")
    private Long categoryId;

    @NotNull(message = "Category name is mandatory")
    @NotBlank(message = "Category name is mandatory")
    private String categoryName;

    @NotNull
    @NotBlank(message = "Description is mandatory")
    @Size(min = 5, max = 50)
    private String description;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be positive number")
    private BigDecimal price;

    @NotNull(message = "Status is mandatory")
    @NotBlank(message = "Status is mandatory")
    private String status;
}
