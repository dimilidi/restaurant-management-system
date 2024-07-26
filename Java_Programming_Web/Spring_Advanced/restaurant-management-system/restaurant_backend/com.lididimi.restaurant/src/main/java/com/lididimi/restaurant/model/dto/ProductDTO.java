package com.lididimi.restaurant.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private Long id;

    @NotNull
    @NotBlank(message = "Product name is mandatory")
    private String name;

    private Long categoryId;

    private String categoryName;

    @NotNull(message = "Description is mandatory")
    @NotBlank(message = "Description is mandatory")
    @Size(min = 5, max = 50)
    private String description;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be positive number")
    private BigDecimal price;

    private String status;
}
