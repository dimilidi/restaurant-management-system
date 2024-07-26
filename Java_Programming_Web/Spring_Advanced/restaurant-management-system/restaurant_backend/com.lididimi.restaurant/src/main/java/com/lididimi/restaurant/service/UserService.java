package com.lididimi.restaurant.service;

import com.lididimi.restaurant.model.dto.*;
import jakarta.mail.MessagingException;

import java.util.List;


public interface UserService {

    List<UserDTO> getAllUsers();

    String update(UserUpdateStatusDTO userUpdateStatusDTO);

    String changePassword(UserChangePasswordDTO userChangePasswordDTO);


}
