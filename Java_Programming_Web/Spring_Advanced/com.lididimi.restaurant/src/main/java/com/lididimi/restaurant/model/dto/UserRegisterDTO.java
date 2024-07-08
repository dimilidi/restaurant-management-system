package com.lididimi.restaurant.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegisterDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 2, message = "Name length must be more than two characters")
    private String NAME;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 2, message = "Password length must be more than two characters")
    private String password;

    @NotBlank(message = "Password is required")
    @Size(min = 2, message = "Password length must be more than two characters")
    private String confirmPassword;

}
