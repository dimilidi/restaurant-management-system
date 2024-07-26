package com.lididimi.restaurant.model.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopEmployeeDTO {
    private String email;
    private String employeeName;
    private long billCount;
}

