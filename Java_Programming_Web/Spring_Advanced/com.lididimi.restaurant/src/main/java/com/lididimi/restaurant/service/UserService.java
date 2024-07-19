package com.lididimi.restaurant.service;

import com.lididimi.restaurant.model.dto.UserChangePasswordDTO;
import com.lididimi.restaurant.model.dto.UserDTO;
import com.lididimi.restaurant.model.dto.UserLoginDTO;
import com.lididimi.restaurant.model.dto.UserRegisterDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    //ResponseEntity<String> register(Map<String, String> requestMap);
    ResponseEntity<String> register(UserRegisterDTO userRegisterDTO);

    ResponseEntity<String> login(UserLoginDTO userLoginDTO);

    ResponseEntity<List<UserDTO>> getAllUsers();

    ResponseEntity<String> update(UserDTO userDTO);

    ResponseEntity<String> checkToken();

    ResponseEntity<String> changePassword(UserChangePasswordDTO userChangePasswordDTO);

    ResponseEntity<String> forgotPassword(UserDTO userDTO);

    boolean validatePasswordResetToken(String token);

    ResponseEntity<String> updatePassword(String token, String newPassword);
}
