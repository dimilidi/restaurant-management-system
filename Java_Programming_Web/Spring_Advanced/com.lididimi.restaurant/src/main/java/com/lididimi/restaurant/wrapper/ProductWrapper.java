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

   public ProductWrapper() {}


}
