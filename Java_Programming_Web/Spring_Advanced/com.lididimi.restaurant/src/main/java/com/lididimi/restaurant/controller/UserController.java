package com.lididimi.restaurant.controller;

import com.lididimi.restaurant.model.dto.*;
import com.lididimi.restaurant.response.SuccessResponse;
import com.lididimi.restaurant.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        UserDTO userDTO = userService.register(userRegisterDTO);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "Successfully registered.", userDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO userLoginDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "Successfully logged in.", userService.login(userLoginDTO));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        String responseMessage = userService.update(userDTO);
        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK.value(), responseMessage));
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody @Valid UserChangePasswordDTO userChangePasswordDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), userService.changePassword(userChangePasswordDTO), null);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestBody @Valid EmailDTO emailDTO, BindingResult bindingResult) throws MessagingException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), userService.forgotPassword(emailDTO), null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/reset-password")
    public boolean validateResetToken(@RequestParam String token) {
        return userService.validatePasswordResetToken(token);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody @Valid ResetPasswordDTO resetPasswordDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        String token = resetPasswordDTO.getToken();
        String newPassword = resetPasswordDTO.getNewPassword();

        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), userService.updatePassword(token, newPassword), null);
        return ResponseEntity.ok(response);
    }
}


