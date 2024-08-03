package com.lididimi.restaurant.model.dto.bestseller;

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