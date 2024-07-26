package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.exception.common.ObjectNotFoundException;
import com.lididimi.restaurant.exception.user.BadCredentialsException;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.jwt.JwtUtils;
import com.lididimi.restaurant.jwt.RestaurantUserDetailsService;
import com.lididimi.restaurant.model.dto.EmailDTO;
import com.lididimi.restaurant.model.dto.UserChangePasswordDTO;
import com.lididimi.restaurant.model.dto.UserDTO;
import com.lididimi.restaurant.model.dto.UserUpdateStatusDTO;
import com.lididimi.restaurant.model.entity.PasswordResetToken;
import com.lididimi.restaurant.model.entity.UserEntity;
import com.lididimi.restaurant.model.enums.StatusNameEnum;
import com.lididimi.restaurant.model.enums.UserRoleNameEnum;
import com.lididimi.restaurant.repository.PasswordResetTokenRepository;
import com.lididimi.restaurant.repository.RoleRepository;
import com.lididimi.restaurant.repository.UserRepository;
import com.lididimi.restaurant.service.PasswordService;
import com.lididimi.restaurant.service.UserService;
import com.lididimi.restaurant.utils.EmailUtils;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@Service
public class PasswordServiceImpl implements PasswordService {
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

    public PasswordServiceImpl(UserRepository userRepository, ModelMapper modelMapper, AuthenticationManager authenticationManager, RestaurantUserDetailsService restaurantUserDetailsService, JwtUtils jwtUtils, PasswordEncoder passwordEncoder, JwtFilter jwtFilter, EmailUtils emailUtils, PasswordResetTokenRepository tokenRepository, RoleRepository roleRepository, HttpServletRequest request) {
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


