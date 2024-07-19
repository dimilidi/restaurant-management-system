package com.lididimi.restaurant.model.dto;

import com.lididimi.restaurant.model.entity.CategoryEntity;
import com.lididimi.restaurant.model.validation.Unique;
import com.lididimi.restaurant.repository.ProductRepository;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ProductDTO {

    @NotNull
    @NotBlank(message = "Product name is mandatory")
    @Unique(fieldName = "name", repository = ProductRepository.class, message = "Product name must be unique")
    private String name;

    @NotNull
    @NotBlank(message = "Category is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity category;

    @NotNull
    @NotBlank(message = "Description is mandatory")
    @Size(min = 5, max = 50)
    private String description;

    @NotNull
    @NotBlank(message = "Price is mandatory")
    @Positive(message = "Price must be positive number")
    private BigDecimal price;

    @NotNull
    @NotBlank(message = "Status is mandatory")
    private String status;
}
