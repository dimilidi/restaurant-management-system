package com.lididimi.restaurant.service;

import com.lididimi.restaurant.model.dto.EmailDTO;
import jakarta.mail.MessagingException;

public interface PasswordService {
    String forgotPassword(EmailDTO emailDTO) throws MessagingException;

    boolean validatePasswordResetToken(String token);

    String updatePassword(String token, String newPassword);
}
