package com.lididimi.restaurant.service;

import com.lididimi.restaurant.model.dto.user.UserDTO;
import com.lididimi.restaurant.model.dto.user.UserLoginDTO;
import com.lididimi.restaurant.model.dto.user.UserRegisterDTO;


public interface AuthService {

    UserDTO register(UserRegisterDTO userRegisterDTO);

    String login(UserLoginDTO userLoginDTO);

}
