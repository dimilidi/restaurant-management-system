package com.lididimi.restaurant.model.dto;

import com.lididimi.restaurant.model.enums.StatusNameEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserUpdateStatusDTO {
    @NotNull(message = "User ID is required")
    private Long id;

    @NotNull(message = "Status is required")
    private StatusNameEnum status;
}
