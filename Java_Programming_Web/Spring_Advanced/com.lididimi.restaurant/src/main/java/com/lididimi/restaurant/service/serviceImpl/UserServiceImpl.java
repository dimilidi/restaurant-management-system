package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.exception.common.ObjectNotFoundException;
import com.lididimi.restaurant.exception.user.*;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.jwt.JwtUtils;
import com.lididimi.restaurant.jwt.RestaurantUserDetailsService;
import com.lididimi.restaurant.model.dto.*;
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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    private final HttpServletRequest request;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, AuthenticationManager authenticationManager, RestaurantUserDetailsService restaurantUserDetailsService, JwtUtils jwtUtils, PasswordEncoder passwordEncoder, JwtFilter jwtFilter, EmailUtils emailUtils, PasswordResetTokenRepository tokenRepository, RoleRepository roleRepository, HttpServletRequest request) {
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
        this.request = request;
    }

    @Transactional
    @Override
    public UserDTO register(UserRegisterDTO userRegisterDTO) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(userRegisterDTO.getEmail());

        if (userOptional.isPresent()) {
            throw new AlreadyExistsException(RestaurantConstants.EMAIL_EXISTS);
        }

        Optional<RoleEntity> optionalRole = roleRepository.findByName(UserRoleNameEnum.ADMIN);
        UserEntity userEntity = modelMapper.map(userRegisterDTO, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        userEntity.setStatus(StatusNameEnum.ACTIVE);

        if (optionalRole.isPresent()) {
            RoleEntity userRole = optionalRole.get();
            userEntity.setRoles(List.of(userRole));
            roleRepository.save(userRole);
        }

        userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserDTO.class);
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword())
            );

            if (auth.isAuthenticated()) {
                log.info("Authentication Success");
                UserEntity userEntity = restaurantUserDetailsService.getUserDetails();
                if (StatusNameEnum.ACTIVE.equals(userEntity.getStatus())) {
                    log.info("Active User");
                    List<String> roles = userEntity.getRoles().stream()
                            .map(roleEntity -> roleEntity.getName().name())
                            .collect(Collectors.toList());

                    String token = jwtUtils.generateToken(userEntity.getEmail(), userEntity.getName(), roles);

                    return token;
                } else {
                    log.info("Wait for admin approval");
                    throw new BadCredentialsException(RestaurantConstants.UNAPPROVED);
                }
            } else {
                log.error("Authentication Failed");
                throw new BadCredentialsException(RestaurantConstants.BAD_CREDENTIALS);
            }
        } catch (BadCredentialsException e) {
            log.error("Invalid credentials: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error during authentication: {}", e.getMessage());
            throw new BadCredentialsException(RestaurantConstants.BAD_CREDENTIALS);
        }
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


    @Override
    public String forgotPassword(EmailDTO emailDTO) throws MessagingException {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(emailDTO.getEmail());
        if (optionalUser.isPresent() && !optionalUser.get().getEmail().isEmpty()) {
            UserEntity user = optionalUser.get();
            emailUtils.forgotMail(user.getEmail(), "Link to reset password", passwordResetToken(user.getEmail()));
            return RestaurantConstants.CHECK_EMAIL;
        } else {
            throw new ObjectNotFoundException(RestaurantConstants.EMAIL_NOT_FOUND);
        }
    }

    @Override
    public boolean validatePasswordResetToken(String token) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(token);
        if (tokenOpt.isPresent()) {
            PasswordResetToken resetToken = tokenOpt.get();
            return !resetToken.isExpired();
        }
        return false;
    }

    @Override
    public String updatePassword(String token, String newPassword) {
        Optional<PasswordResetToken> optionalToken = tokenRepository.findByToken(token);
        if (optionalToken.isPresent()) {
            PasswordResetToken resetToken = optionalToken.get();
            UserEntity user = resetToken.getUser();
            if (resetToken.isExpired()) {
                throw new BadCredentialsException(RestaurantConstants.TOKEN_EXPIRED);
            }
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return RestaurantConstants.PASSWORD_UPDATE_SUCCESS;
        } else {
            throw new BadCredentialsException(RestaurantConstants.TOKEN_EXPIRED);
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


    private String passwordResetToken(String email) throws MessagingException {
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
            resetUrl = RestaurantConstants.TOKEN_RESET_URL_BASE + token;
        }
        return resetUrl;
    }
}


