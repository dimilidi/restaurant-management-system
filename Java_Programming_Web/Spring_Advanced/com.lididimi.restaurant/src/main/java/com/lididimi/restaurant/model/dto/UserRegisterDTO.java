package com.lididimi.restaurant.model.dto;

import com.lididimi.restaurant.model.validation.FieldMatch;
import com.lididimi.restaurant.model.validation.UniqueUserEmail;
import jakarta.validation.constraints.*;
import lombok.*;

@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message= "Passwords should match"
)
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 2, message = "Name length must be more than two characters")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Name must consist only of letters")
    private String name;

    @NotNull(message = "Phone number is required")
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^0[0-9]{9}$", message = "Phone number must start with 0 and be exactly 10 digits")
    private String contactNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @UniqueUserEmail
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 2, message = "Password length must be more than two characters")
    private String password;

    @NotBlank(message = "Password is required")
    @Size(min = 2, message = "Password length must be more than two characters")
    private String confirmPassword;

}
