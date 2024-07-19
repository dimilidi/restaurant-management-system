package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.jwt.JwtUtils;
import com.lididimi.restaurant.jwt.RestaurantUserDetailsService;
import com.lididimi.restaurant.model.dto.UserChangePasswordDTO;
import com.lididimi.restaurant.model.dto.UserDTO;
import com.lididimi.restaurant.model.dto.UserLoginDTO;
import com.lididimi.restaurant.model.dto.UserRegisterDTO;
import com.lididimi.restaurant.model.entity.PasswordResetToken;
import com.lididimi.restaurant.model.entity.RoleEntity;
import com.lididimi.restaurant.model.entity.UserEntity;
import com.lididimi.restaurant.model.enums.StatusNameEnum;
import com.lididimi.restaurant.model.enums.UserRoleNameEnum;
import com.lididimi.restaurant.repository.PasswordResetTokenRepository;
import com.lididimi.restaurant.repository.RoleRepository;
import com.lididimi.restaurant.repository.UserRepository;
import com.lididimi.restaurant.service.UserService;
import com.lididimi.restaurant.utils.EmailUtils;
import com.lididimi.restaurant.utils.RestaurantUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    public final RestaurantUserDetailsService restaurantUserDetailsService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final JwtFilter jwtFilter;
    private final EmailUtils emailUtils;
    private final PasswordResetTokenRepository tokenRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, AuthenticationManager authenticationManager, RestaurantUserDetailsService restaurantUserDetailsService, JwtUtils jwtUtils, PasswordEncoder passwordEncoder, JwtFilter jwtFilter, EmailUtils emailUtils, PasswordResetTokenRepository tokenRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.restaurantUserDetailsService = restaurantUserDetailsService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.jwtFilter = jwtFilter;
        this.emailUtils = emailUtils;
        this.tokenRepository = tokenRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public ResponseEntity<String> register(UserRegisterDTO userRegisterDTO) {
        log.info("Inside register {}", userRegisterDTO);

        try {
            Optional<UserEntity> userOptional = userRepository.findByEmail(userRegisterDTO.getEmail());

            if (userOptional.isEmpty()) {
                Optional<RoleEntity> optionalRole = roleRepository.findByName(UserRoleNameEnum.ADMIN);
                UserEntity userEntity = modelMapper.map(userRegisterDTO, UserEntity.class);
                userEntity.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
                userEntity.setStatus(StatusNameEnum.ACTIVE);
                if(optionalRole.isPresent()) {
                    RoleEntity userRole = optionalRole.get();
                    userEntity.setRoles(List.of(userRole));
                    roleRepository.save(userRole);
                }
                userRepository.save(userEntity);

                return RestaurantUtils.getResponseEntity("{\"message\": \"Successfully registered.\"}", HttpStatus.OK);
            } else {
                return RestaurantUtils.getResponseEntity("{\"message\": \"Email already exists.\"}", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> login(UserLoginDTO userLoginDTO) {
        log.info("Inside login");
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword())
            );

            if (auth.isAuthenticated()) {
                UserEntity userEntity = restaurantUserDetailsService.getUserDetails();
                if (StatusNameEnum.ACTIVE.equals(userEntity.getStatus())) {
                    List<String> roles = userEntity.getRoles().stream()
                            .map(roleEntity -> roleEntity.getName().name())
                            .collect(Collectors.toList());

                    String token = jwtUtils.generateToken(userEntity.getEmail(), userEntity.getName(), roles);

                    return new ResponseEntity<>(String.format("{\"token\": \"%s\"}", token), HttpStatus.OK);
                } else {
                    log.info("Wait for admin approval");
                    return new ResponseEntity<>("{\"message\": \"Wait for admin approval.\"}", HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception e) {
            log.error("{}", e);
        }
        return new ResponseEntity<>("{\"message\": \"Bad Credentials.\"}", HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            if (jwtFilter.isAdmin()) {
                List<UserEntity> users = userRepository.getAllUsers(UserRoleNameEnum.USER);
                List<UserDTO> allUsers = users.stream()
                        .map(this::convertToDto)
                        .collect(Collectors.toList());
                return new ResponseEntity<>(allUsers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private UserDTO convertToDto(UserEntity user) {
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public ResponseEntity<String> update(UserDTO userDTO) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<UserEntity> optionalUser = userRepository.findById(userDTO.getId());
                if (optionalUser.isPresent()) {
                    StatusNameEnum status = userDTO.getStatus();
                    userRepository.updateStatus(status, userDTO.getId());
                    sendMailToAllAdmins(status, optionalUser.get().getEmail(), userRepository.getAllAdmins(UserRoleNameEnum.ADMIN));
                    return RestaurantUtils.getResponseEntity("{\"message\": \"Successfully updated user status.\"}", HttpStatus.OK);
                } else {
                    return RestaurantUtils.getResponseEntity("{\"message\": \"User does not exist.\"}", HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("{\"message\": \"Bad Credentials.\"}", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
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

    @Override
    public ResponseEntity<String> checkToken() {
        return RestaurantUtils.getResponseEntity("ACTIVE", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> changePassword(UserChangePasswordDTO userChangePasswordDTO) {
        try {
            Optional<UserEntity> optionalUser = userRepository.findByEmail(jwtFilter.currentUser());
            if (optionalUser.isPresent()) {
                UserEntity user = optionalUser.get();
                if (userChangePasswordDTO.getOldPassword() == null || userChangePasswordDTO.getNewPassword() == null) {
                    return RestaurantUtils.getResponseEntity("{\"message\": \"Password fields cannot be null.\"}", HttpStatus.BAD_REQUEST);
                }

                if (passwordEncoder.matches(userChangePasswordDTO.getOldPassword(), user.getPassword())) {
                    user.setPassword(passwordEncoder.encode(userChangePasswordDTO.getNewPassword()));
                    userRepository.save(user);
                    return RestaurantUtils.getResponseEntity("{\"message\": \"Successfully updated password.\"}", HttpStatus.OK);
                } else {
                    return RestaurantUtils.getResponseEntity("{\"message\": \"Incorrect old password.\"}", HttpStatus.BAD_REQUEST);
                }
            } else {
                return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> forgotPassword(UserDTO userDTO) {
        try {
            Optional<UserEntity> optionalUser = userRepository.findByEmail(userDTO.getEmail());
            if (optionalUser.isPresent() && !optionalUser.get().getEmail().isEmpty()) {
                UserEntity user = optionalUser.get();
                emailUtils.forgotMail(user.getEmail(), "Link to reset password", passwordResetToken(user.getEmail()));
            }
            return RestaurantUtils.getResponseEntity("{\"message\": \"Check your email for link to reset password.\"}", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public String passwordResetToken(String email) throws MessagingException {
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);
        String resetUrl = "";
        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            String token = UUID.randomUUID().toString();
            PasswordResetToken resetToken = new PasswordResetToken();
            resetToken.setToken(token);
            resetToken.setUser(user);
            resetToken.setExpiryDate(new Date(System.currentTimeMillis() + 3600000)); // 1 hour expiry
            tokenRepository.save(resetToken);
            resetUrl = "http://localhost:4200/reset-password?token=" + token;
        }
        return resetUrl;
    }


    public boolean validatePasswordResetToken(String token) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(token);
        if (tokenOpt.isPresent()) {
            PasswordResetToken resetToken = tokenOpt.get();
            return !resetToken.isExpired();
        }
        return false;
    }


    @Override
    public ResponseEntity<String> updatePassword(String token, String newPassword) {
        try {
            Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(token);
            if (tokenOpt.isPresent()) {
                PasswordResetToken resetToken = tokenOpt.get();
                if (resetToken.isExpired()) {
                    return RestaurantUtils.getResponseEntity("{\"message\": \"Token has expired.\"}", HttpStatus.BAD_REQUEST);
                }
                UserEntity user = resetToken.getUser();
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
                tokenRepository.delete(resetToken);
                return RestaurantUtils.getResponseEntity("{\"message\": \"Password reset successful.\"}", HttpStatus.OK);
            } else {
                return RestaurantUtils.getResponseEntity("{\"message\": \"Invalid token.\"}", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

