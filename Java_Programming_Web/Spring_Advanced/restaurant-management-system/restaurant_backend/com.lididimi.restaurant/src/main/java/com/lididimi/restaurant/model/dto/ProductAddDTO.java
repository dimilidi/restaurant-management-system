package com.lididimi.restaurant.model.dto;

import com.lididimi.restaurant.model.entity.ProductEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductAddDTO extends ProductEntity {
    private Long id;

    @NotNull(message = "Product name is mandatory")
    @NotBlank(message = "Product name is mandatory")
    @Size(min = 2, max = 20, message = "Product name must have min 2 and max 20 characters")
    private String name;

    @NotNull(message = "Category id is mandatory")
    private Long categoryId;

    @NotNull(message = "Description is mandatory")
    @NotBlank(message = "Description is mandatory")
    @Size(min = 5, max = 50, message = "Description must have min 5 and max 50 characters")
    private String description;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be positive number")
    private BigDecimal price;
}
