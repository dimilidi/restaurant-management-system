package com.lididimi.restaurant.wrapper;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductWrapper {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String status;
    private Long categoryId;
    private String categoryName;

    public ProductWrapper() {
    }

    public ProductWrapper(Long id, String name, String description, BigDecimal price, String status, Long categoryId, String categoryName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }


}
