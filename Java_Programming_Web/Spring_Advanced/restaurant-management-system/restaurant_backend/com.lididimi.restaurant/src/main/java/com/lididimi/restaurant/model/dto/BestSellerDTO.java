package com.lididimi.restaurant.model.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BestSellerDTO {
    private String name;
    private int sales;
}