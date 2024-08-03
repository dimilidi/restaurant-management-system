package com.lididimi.restaurant.model.dto;

import com.lididimi.restaurant.model.entity.RoleEntity;
import com.lididimi.restaurant.model.enums.StatusNameEnum;
import com.lididimi.restaurant.model.enums.UserRoleNameEnum;
import lombok.Data;

import java.util.ArrayList;

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
