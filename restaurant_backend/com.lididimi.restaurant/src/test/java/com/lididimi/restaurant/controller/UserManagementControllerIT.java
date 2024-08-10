package com.lididimi.restaurant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.model.dto.user.UserChangePasswordDTO;
import com.lididimi.restaurant.model.dto.user.UserDTO;
import com.lididimi.restaurant.model.dto.user.UserUpdateStatusDTO;
import com.lididimi.restaurant.model.enums.StatusNameEnum;
import com.lididimi.restaurant.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(properties = {
        "smtp_username=dummyUsername",
        "smtp_password=dummyPassword",
})
@AutoConfigureMockMvc
public class UserManagementControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private UserDTO userDTO;
    private UserChangePasswordDTO userChangePasswordDTO;
    private UserUpdateStatusDTO userUpdateStatusDTO;

    @BeforeEach
    public void setUp() {
        userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setEmail("testuser@example.com");
        userDTO.setName("Test User");
        userDTO.setContactNumber("0123456789");
        userDTO.setStatus(StatusNameEnum.ACTIVE);

        userUpdateStatusDTO = new UserUpdateStatusDTO();
        userUpdateStatusDTO.setId(1L);
        userUpdateStatusDTO.setStatus(StatusNameEnum.ACTIVE);

        userChangePasswordDTO = new UserChangePasswordDTO();
        userChangePasswordDTO.setId(1L);
        userChangePasswordDTO.setOldPassword("oldPassword");
        userChangePasswordDTO.setNewPassword("newPassword");
        userChangePasswordDTO.setConfirmPassword("confirmPassword");;
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetAllUsers() throws Exception {
        List<UserDTO> users = Arrays.asList(userDTO);

        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/get"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].email").value("testuser@example.com"))
                .andExpect(jsonPath("$[0].name").value("Test User"))
                .andExpect(jsonPath("$[0].contactNumber").value("0123456789"))
                .andExpect(jsonPath("$[0].status").value("ACTIVE"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateUserStatus_Success() throws Exception {
        when(userService.update(any(UserUpdateStatusDTO.class))).thenReturn(RestaurantConstants.USER_UPDATE_SUCCESS);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateStatusDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(RestaurantConstants.USER_UPDATE_SUCCESS));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateUserStatus_ValidationError() throws Exception {
        userUpdateStatusDTO.setStatus(null); // Invalid status

        mockMvc.perform(MockMvcRequestBuilders.post("/users/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateStatusDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").exists());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testChangePassword_Success() throws Exception {
        when(userService.changePassword(any(UserChangePasswordDTO.class))).thenReturn(RestaurantConstants.PASSWORD_UPDATE_SUCCESS);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/changePassword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userChangePasswordDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(RestaurantConstants.PASSWORD_UPDATE_SUCCESS));
    }


    @Test
    @WithMockUser(roles = "USER")
    public void testChangePassword_ValidationError() throws Exception {
        userChangePasswordDTO.setNewPassword(""); // Invalid new password

        mockMvc.perform(MockMvcRequestBuilders.post("/users/changePassword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userChangePasswordDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.newPassword").exists());
    }
}



