package com.lididimi.restaurant.model.dto;

import com.lididimi.restaurant.model.enums.StatusNameEnum;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    private String name;

    private String email;

    private String contactNumber;

    private StatusNameEnum status;

    public UserDTO() {}

    public UserDTO(Long id, String name, String email, String contactNumber, StatusNameEnum status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.status = status;
    }
}
