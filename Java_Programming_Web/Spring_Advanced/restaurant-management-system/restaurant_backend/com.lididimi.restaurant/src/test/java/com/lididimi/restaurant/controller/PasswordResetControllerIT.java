/*
package com.lididimi.restaurant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lididimi.restaurant.model.dto.EmailDTO;
import com.lididimi.restaurant.model.dto.ResetPasswordDTO;
import com.lididimi.restaurant.service.UserService;
import com.lididimi.restaurant.model.response.SuccessResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PasswordResetControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private EmailDTO emailDTO;
    private ResetPasswordDTO resetPasswordDTO;

    @BeforeEach
    public void setUp() {
        emailDTO = new EmailDTO();
        emailDTO.setEmail("testuser@example.com");

        resetPasswordDTO = new ResetPasswordDTO();
        resetPasswordDTO.setToken("reset-token");
        resetPasswordDTO.setNewPassword("newPassword123");
    }

    @Test
    public void testForgotPassword_Success() throws Exception {
        String successMessage = "Password reset email sent successfully";

        when(userService.forgotPassword(any(EmailDTO.class))).thenReturn(successMessage);

        mockMvc.perform(post("/password/forgot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emailDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value(successMessage))
                .andExpect(jsonPath("$.data").doesNotExist()); // No data expected
    }

    @Test
    public void testForgotPassword_ValidationError() throws Exception {
        emailDTO.setEmail(""); // Invalid email

        mockMvc.perform(post("/password/forgot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emailDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.email").exists());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testValidateResetToken_Success() throws Exception {
        when(userService.validatePasswordResetToken("valid-token")).thenReturn(true);

        mockMvc.perform(get("/password/reset-token")
                        .param("token", "valid-token"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testValidateResetToken_InvalidToken() throws Exception {
        when(userService.validatePasswordResetToken("invalid-token")).thenReturn(false);

        mockMvc.perform(get("/password/reset-token")
                        .param("token", "invalid-token"))
                .andExpect(status().isOk());
    }

    @Test
    public void testResetPassword_Success() throws Exception {
        String successMessage = "Password reset successfully";

        when(userService.updatePassword(any(String.class), any(String.class))).thenReturn(successMessage);

        mockMvc.perform(post("/password/reset")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resetPasswordDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value(successMessage))
                .andExpect(jsonPath("$.data").doesNotExist()); // No data expected
    }

    @Test
    public void testResetPassword_ValidationError() throws Exception {
        resetPasswordDTO.setNewPassword(""); // Invalid password

        mockMvc.perform(post("/password/reset")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resetPasswordDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.newPassword").exists());
    }
}

*/


package com.lididimi.restaurant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lididimi.restaurant.model.dto.EmailDTO;
import com.lididimi.restaurant.model.dto.ResetPasswordDTO;
import com.lididimi.restaurant.service.PasswordService;
import com.lididimi.restaurant.service.serviceImpl.PasswordServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PasswordResetControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PasswordService passwordService;

    private EmailDTO emailDTO;
    private ResetPasswordDTO resetPasswordDTO;


    @BeforeEach
    public void setUp() {
        emailDTO = new EmailDTO();
        emailDTO.setEmail("testuser@example.com");

        resetPasswordDTO = new ResetPasswordDTO();
        resetPasswordDTO.setToken("reset-token");
        resetPasswordDTO.setNewPassword("newPassword123");
    }

    @Test
    public void testForgotPassword_Success() throws Exception {
        String successMessage = "Password reset email sent successfully";

        when(passwordService.forgotPassword(any(EmailDTO.class))).thenReturn(successMessage);

        mockMvc.perform(post("/password/forgot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emailDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value(successMessage))
                .andExpect(jsonPath("$.data").doesNotExist()); // No data expected
    }

    @Test
    public void testForgotPassword_ValidationError() throws Exception {
        emailDTO.setEmail(""); // Invalid email

        Map<String, String> errors = new HashMap<>();
        errors.put("email", "Email is required");

        mockMvc.perform(post("/password/forgot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emailDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.email").value("Email is required"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testValidateResetToken_Success() throws Exception {
        when(passwordService.validatePasswordResetToken("valid-token")).thenReturn(true);

        mockMvc.perform(get("/password/reset-token")
                        .param("token", "valid-token"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testValidateResetToken_InvalidToken() throws Exception {
        when(passwordService.validatePasswordResetToken("invalid-token")).thenReturn(false);

        mockMvc.perform(get("/password/reset-token")
                        .param("token", "invalid-token"))
                .andExpect(status().isOk());
    }

    @Test
    public void testResetPassword_Success() throws Exception {
        String successMessage = "Password reset successfully";

        when(passwordService.updatePassword(any(String.class), any(String.class))).thenReturn(successMessage);

        mockMvc.perform(post("/password/reset")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resetPasswordDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value(successMessage))
                .andExpect(jsonPath("$.data").doesNotExist()); // No data expected
    }

    @Test
    public void testResetPassword_ValidationError() throws Exception {
        resetPasswordDTO.setNewPassword(""); // Invalid password

        Map<String, String> errors = new HashMap<>();
        errors.put("newPassword", "New Password required");

        mockMvc.perform(post("/password/reset")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resetPasswordDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.newPassword").value("New Password required"));
    }
}
