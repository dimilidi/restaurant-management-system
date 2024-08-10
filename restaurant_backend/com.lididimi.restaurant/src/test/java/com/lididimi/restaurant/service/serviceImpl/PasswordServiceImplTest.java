package com.lididimi.restaurant.service.serviceImpl;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.exception.common.ObjectNotFoundException;
import com.lididimi.restaurant.exception.user.BadCredentialsException;
import com.lididimi.restaurant.model.dto.email.EmailDTO;
import com.lididimi.restaurant.model.entity.PasswordResetToken;
import com.lididimi.restaurant.model.entity.UserEntity;
import com.lididimi.restaurant.repository.PasswordResetTokenRepository;
import com.lididimi.restaurant.repository.UserRepository;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordResetTokenRepository tokenRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmailServiceImpl emailUtils;

    @Mock
    private JavaMailSender emailSender;

    @InjectMocks
    private PasswordServiceImpl passwordService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testForgotPassword_Success() throws MessagingException {
        // Given
        String email = "user@example.com";
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmail(email);

        UserEntity user = new UserEntity();
        user.setEmail(email);

        // Mock user repository to return a user for the given email
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // Mock EmailUtils to perform the expected action without actual email sending
        doNothing().when(emailUtils).forgotMail(anyString(), anyString(), anyString());

        // When
        String result = passwordService.forgotPassword(emailDTO);

        // Then
        assertEquals(RestaurantConstants.CHECK_EMAIL, result);

        // Verify emailUtils.forgotMail was called with correct parameters
        verify(emailUtils).forgotMail(eq(email), eq("Link to reset password"), anyString());
    }

    @Test
    public void testForgotPassword_EmailNotFound() throws MessagingException {
        // Given
        String email = "user@example.com";
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmail(email);

        // Mock user repository to return an empty result for the given email
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // When & Then
        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            passwordService.forgotPassword(emailDTO);
        });
        assertEquals(RestaurantConstants.EMAIL_NOT_FOUND, exception.getMessage());
    }

    @Test
    public void testValidatePasswordResetToken_ValidToken() {
        // Given
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setExpiryDate(new Date(System.currentTimeMillis() + 3600000)); // 1 hour expiry

        // Mock token repository to return a valid token
        when(tokenRepository.findByToken(token)).thenReturn(Optional.of(resetToken));

        // When
        boolean isValid = passwordService.validatePasswordResetToken(token);

        // Then
        assertTrue(isValid);
    }

    @Test
    public void testUpdatePassword_Success() {
        // Given
        String token = UUID.randomUUID().toString();
        String newPassword = "newPassword";
        UserEntity user = new UserEntity();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setUser(user);
        resetToken.setExpiryDate(new Date(System.currentTimeMillis() + 3600000)); // 1 hour expiry

        // Mock token repository to return a valid token
        when(tokenRepository.findByToken(token)).thenReturn(Optional.of(resetToken));
        when(passwordEncoder.encode(newPassword)).thenReturn("encodedNewPassword");
        when(userRepository.save(user)).thenReturn(user);

        // When
        String result = passwordService.updatePassword(token, newPassword);

        // Then
        assertEquals(RestaurantConstants.PASSWORD_UPDATE_SUCCESS, result);
        verify(userRepository).save(user);
    }

    @Test
    public void testUpdatePassword_TokenExpired() {
        // Given
        String token = UUID.randomUUID().toString();
        PasswordResetToken expiredToken = new PasswordResetToken();
        expiredToken.setExpiryDate(new Date(System.currentTimeMillis() - 3600000)); // 1 hour ago

        // Mock token repository to return an expired token
        when(tokenRepository.findByToken(token)).thenReturn(Optional.of(expiredToken));

        // When & Then
        Exception exception = assertThrows(BadCredentialsException.class, () -> {
            passwordService.updatePassword(token, "newPassword");
        });
        assertEquals(RestaurantConstants.TOKEN_EXPIRED, exception.getMessage());
    }
}
