package com.lididimi.restaurant.service;

import com.lididimi.restaurant.model.dto.user.UserChangePasswordDTO;
import com.lididimi.restaurant.model.dto.user.UserDTO;
import com.lididimi.restaurant.model.dto.user.UserUpdateStatusDTO;

import java.util.List;


public interface UserService {

    List<UserDTO> getAllUsers();

    String update(UserUpdateStatusDTO userUpdateStatusDTO);

    String changePassword(UserChangePasswordDTO userChangePasswordDTO);

}
