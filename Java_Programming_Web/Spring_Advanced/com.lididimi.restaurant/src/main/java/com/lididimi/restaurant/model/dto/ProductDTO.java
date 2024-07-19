package com.lididimi.restaurant.model.dto;

import com.lididimi.restaurant.model.entity.CategoryEntity;
import com.lididimi.restaurant.model.enums.StatusNameEnum;
import com.lididimi.restaurant.model.validation.Unique;
import com.lididimi.restaurant.repository.ProductRepository;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
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

    @NotNull
    @NotBlank(message = "Description is mandatory")
    @Size(min = 5, max = 50)
    private String description;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be positive number")
    private BigDecimal price;

    private String status;
}
