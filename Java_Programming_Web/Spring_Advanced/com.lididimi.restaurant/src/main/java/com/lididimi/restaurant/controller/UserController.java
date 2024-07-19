package com.lididimi.restaurant.controller;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.model.dto.*;
import com.lididimi.restaurant.service.UserService;
import com.lididimi.restaurant.utils.RestaurantUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
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
        try {
            return userService.register(userRegisterDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO userLoginDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            return userService.login(userLoginDTO);
        } catch (Exception e) {
            e.printStackTrace();
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            return userService.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<List<UserDTO>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) {
        try {
            return userService.update(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/checkToken")
    public ResponseEntity<String> checkToken() {
        try {
            userService.checkToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody @Valid UserChangePasswordDTO userChangePasswordDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
           // return RestaurantUtils.getResponseEntity("{\"message\": \"Validation errors.\"}", HttpStatus.BAD_REQUEST);
        }

        try {
            return userService.changePassword(userChangePasswordDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestBody @Valid UserDTO userDTO) {
        try {
            return userService.forgotPassword(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/reset-password")
    public boolean validateResetToken(@RequestParam String token) {
        return userService.validatePasswordResetToken(token);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody @Valid ResetPasswordDTO resetPasswordDTO) {
        String token = resetPasswordDTO.getToken();
        String newPassword = resetPasswordDTO.getNewPassword();

        if (token == null || newPassword == null) {
            return new ResponseEntity<>( "{\"message\": \"Token and new password must be provided.\"}", HttpStatus.BAD_REQUEST);
        }

        try {
            userService.updatePassword(token, newPassword);
            return ResponseEntity.ok("{\"message\": \"Password updated successfully\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return RestaurantUtils.getResponseEntity("{\"message\": \"Failed to update password.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
