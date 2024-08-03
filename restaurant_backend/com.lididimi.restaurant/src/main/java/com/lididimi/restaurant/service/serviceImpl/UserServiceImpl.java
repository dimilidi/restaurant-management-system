package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.exception.common.ObjectNotFoundException;
import com.lididimi.restaurant.exception.user.*;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.jwt.RestaurantUserDetailsService;
import com.lididimi.restaurant.model.dto.user.UserChangePasswordDTO;
import com.lididimi.restaurant.model.dto.user.UserDTO;
import com.lididimi.restaurant.model.dto.user.UserUpdateStatusDTO;
import com.lididimi.restaurant.model.entity.UserEntity;
import com.lididimi.restaurant.model.enums.StatusNameEnum;
import com.lididimi.restaurant.model.enums.UserRoleNameEnum;
import com.lididimi.restaurant.repository.UserRepository;
import com.lididimi.restaurant.service.UserService;
import com.lididimi.restaurant.utils.EmailUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public final RestaurantUserDetailsService restaurantUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtFilter jwtFilter;
    private final EmailUtils emailUtils;


    public UserServiceImpl(
            UserRepository userRepository,
            ModelMapper modelMapper,
            RestaurantUserDetailsService restaurantUserDetailsService,
            PasswordEncoder passwordEncoder,
            JwtFilter jwtFilter,
            EmailUtils emailUtils
    ) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.restaurantUserDetailsService = restaurantUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtFilter = jwtFilter;
        this.emailUtils = emailUtils;
    }


    @Override
    public List<UserDTO> getAllUsers() {
        if (jwtFilter.isAdmin()) {
            List<UserEntity> users = userRepository.getAllUsers(UserRoleNameEnum.USER);
            List<UserDTO> allUsers = users.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
            return allUsers;
        } else {
            throw new BadCredentialsException(RestaurantConstants.UNAUTHORIZED_ACCESS);
        }
    }

    @Override
    public String update(UserUpdateStatusDTO updateStatusDTO) {
        if (jwtFilter.isAdmin()) {
            Optional<UserEntity> optionalUser = userRepository.findById(updateStatusDTO.getId());
            if (optionalUser.isPresent()) {
                StatusNameEnum status = updateStatusDTO.getStatus();
                userRepository.updateStatus(status, updateStatusDTO.getId());
                sendMailToAllAdmins(status, optionalUser.get().getEmail(), userRepository.getAllAdmins(UserRoleNameEnum.ADMIN));
                return RestaurantConstants.USER_UPDATE_SUCCESS;
            } else {
                throw new ObjectNotFoundException(RestaurantConstants.USER_NOT_FOUND);
            }
        } else {
            throw new BadCredentialsException(RestaurantConstants.UNAUTHORIZED_ACCESS);
        }
    }

    @Override
    public String changePassword(UserChangePasswordDTO userChangePasswordDTO) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(jwtFilter.currentUser());
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            if (passwordEncoder.matches(userChangePasswordDTO.getOldPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(userChangePasswordDTO.getNewPassword()));
                userRepository.save(user);
                return RestaurantConstants.PASSWORD_UPDATE_SUCCESS;
            } else {
                throw new BadCredentialsException(RestaurantConstants.INCORRECT_OLD_PASSWORD);
            }
        } else {
                throw new ObjectNotFoundException(RestaurantConstants.USER_NOT_FOUND);
        }
    }

    private UserDTO convertToDto(UserEntity user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private void sendMailToAllAdmins(StatusNameEnum status, String user, List<String> allAdmins) {
        log.info("Send mail to all admins");
        allAdmins.remove(jwtFilter.currentUser());

        if (status != null && status.equals(StatusNameEnum.ACTIVE)) {
            emailUtils.sendSimpleMessage(jwtFilter.currentUser(), "Account approved.", "User: " + user + "\nis approved by\nAdmin: " + jwtFilter.currentUser(), allAdmins);
        } else {
            emailUtils.sendSimpleMessage(jwtFilter.currentUser(), "Account disabled.", "User: " + user + "\nis disabled by\nAdmin: " + jwtFilter.currentUser(), allAdmins);
        }
    }
}


