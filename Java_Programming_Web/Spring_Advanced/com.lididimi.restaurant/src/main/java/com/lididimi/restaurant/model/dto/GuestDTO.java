package com.lididimi.restaurant.model.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuestDTO {
    private String email;
    private String name;
    private Long billCount;
    private List<String> topProducts;
}