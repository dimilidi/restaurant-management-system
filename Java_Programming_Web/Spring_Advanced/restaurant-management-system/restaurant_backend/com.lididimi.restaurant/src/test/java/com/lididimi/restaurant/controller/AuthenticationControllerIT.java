package com.lididimi.restaurant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lididimi.restaurant.model.dto.UserLoginDTO;
import com.lididimi.restaurant.model.dto.UserRegisterDTO;
import com.lididimi.restaurant.model.dto.UserDTO;
import com.lididimi.restaurant.service.AuthService;
import com.lididimi.restaurant.service.serviceImpl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    private UserRegisterDTO userRegisterDTO;
    private UserLoginDTO userLoginDTO;
    private UserDTO userDTO;


    @BeforeEach
    public void setUp() {
        userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setEmail("testuser@example.com");
        userRegisterDTO.setPassword("password123");
        userRegisterDTO.setName("Test User");

        userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("testuser@example.com");
        userLoginDTO.setPassword("password123");

        userDTO = new UserDTO();
        userDTO.setEmail("testuser@example.com");
    }


    @Test
    public void testRegister_Success() throws Exception {
        userRegisterDTO.setContactNumber("0123456789");
        userRegisterDTO.setConfirmPassword("password123");

        when(authService.register(any(UserRegisterDTO.class))).thenReturn(userDTO);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegisterDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully registered."))
                .andExpect(jsonPath("$.data.email").value("testuser@example.com"));
    }


    @Test
    public void testRegister_ValidationError() throws Exception {
        userRegisterDTO.setEmail(""); // Invalid email

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegisterDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.email").exists());
    }

    @Test
    public void testLogin_Success() throws Exception {
        String token = "mocked-jwt-token";
        when(authService.login(any(UserLoginDTO.class))).thenReturn(token);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userLoginDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully logged in."))
                .andExpect(jsonPath("$.data").value(token));
    }

    @Test
    public void testLogin_ValidationError() throws Exception {
        userLoginDTO.setPassword(""); // Invalid password

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userLoginDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.password").exists());
    }
}
