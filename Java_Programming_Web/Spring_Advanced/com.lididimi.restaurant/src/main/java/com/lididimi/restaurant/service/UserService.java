package com.lididimi.restaurant.service;

import com.lididimi.restaurant.model.dto.*;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    UserDTO register(UserRegisterDTO userRegisterDTO);

    String login(UserLoginDTO userLoginDTO);

    List<UserDTO> getAllUsers();

    String update(UserDTO userDTO);

    String changePassword(UserChangePasswordDTO userChangePasswordDTO);

    String forgotPassword(EmailDTO emailDTO) throws MessagingException;

    boolean validatePasswordResetToken(String token);

    String updatePassword(String token, String newPassword);
}
