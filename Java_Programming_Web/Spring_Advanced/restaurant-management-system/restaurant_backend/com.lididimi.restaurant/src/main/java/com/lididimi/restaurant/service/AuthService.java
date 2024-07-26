package com.lididimi.restaurant.service;

import com.lididimi.restaurant.model.dto.*;
import jakarta.mail.MessagingException;

import java.util.List;


public interface AuthService {

    UserDTO register(UserRegisterDTO userRegisterDTO);

    String login(UserLoginDTO userLoginDTO);
}
