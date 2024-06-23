package bg.softuni.paintingcollectors.model.dto.user;

import bg.softuni.paintingcollectors.vallidation.FieldMatch;
import bg.softuni.paintingcollectors.vallidation.UniqueUserEmail;
import jakarta.validation.constraints.*;

@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message= "Passwords should match"
)
public class UserRegisterDTO {

    @NotNull( message = "Username cannot be empty")
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    @UniqueUserEmail
    private String username;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    @UniqueUserEmail
    private String email;

    @NotNull( message = "Password cannot be empty")
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters")
    private String password;

    @NotNull( message = "Password cannot be empty")
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters")
    private String confirmPassword;

    public UserRegisterDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
