package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.exception.common.ObjectNotFoundException;
import com.lididimi.restaurant.exception.user.BadCredentialsException;
import com.lididimi.restaurant.model.dto.email.EmailDTO;
import com.lididimi.restaurant.model.entity.PasswordResetToken;
import com.lididimi.restaurant.model.entity.UserEntity;
import com.lididimi.restaurant.repository.PasswordResetTokenRepository;
import com.lididimi.restaurant.repository.UserRepository;
import com.lididimi.restaurant.service.PasswordService;
import com.lididimi.restaurant.utils.EmailUtils;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
public class PasswordServiceImpl implements PasswordService {
    private final UserRepository userRepository;

    public final RestaurantUserDetailsService restaurantUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final EmailUtils emailUtils;
    private final PasswordResetTokenRepository tokenRepository;


    public PasswordServiceImpl(
            UserRepository userRepository,
            RestaurantUserDetailsService restaurantUserDetailsService,
            PasswordEncoder passwordEncoder,
            EmailUtils emailUtils,
            PasswordResetTokenRepository tokenRepository
    ) {
        this.userRepository = userRepository;
        this.restaurantUserDetailsService = restaurantUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.emailUtils = emailUtils;
        this.tokenRepository = tokenRepository;
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


