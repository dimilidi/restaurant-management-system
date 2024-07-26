package com.lididimi.restaurant.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ForgotPasswordDTO {
    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String email;
}
