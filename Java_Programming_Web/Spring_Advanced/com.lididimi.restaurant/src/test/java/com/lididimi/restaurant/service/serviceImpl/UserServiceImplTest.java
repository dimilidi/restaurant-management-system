package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.exception.common.ObjectNotFoundException;
import com.lididimi.restaurant.exception.user.BadCredentialsException;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.jwt.JwtUtils;
import com.lididimi.restaurant.jwt.RestaurantUserDetailsService;
import com.lididimi.restaurant.model.dto.UserChangePasswordDTO;
import com.lididimi.restaurant.model.dto.UserDTO;
import com.lididimi.restaurant.model.dto.UserUpdateStatusDTO;
import com.lididimi.restaurant.model.entity.UserEntity;
import com.lididimi.restaurant.model.enums.StatusNameEnum;
import com.lididimi.restaurant.model.enums.UserRoleNameEnum;
import com.lididimi.restaurant.repository.PasswordResetTokenRepository;
import com.lididimi.restaurant.repository.RoleRepository;
import com.lididimi.restaurant.repository.UserRepository;
import com.lididimi.restaurant.utils.EmailUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private RestaurantUserDetailsService restaurantUserDetailsService;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtFilter jwtFilter;

    @Mock
    private EmailUtils emailUtils;

    @Mock
    private PasswordResetTokenRepository tokenRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers_Success() {
        // Arrange
        when(jwtFilter.isAdmin()).thenReturn(true);
        UserEntity user = new UserEntity();
        UserDTO userDTO = new UserDTO();
        when(userRepository.getAllUsers(UserRoleNameEnum.USER)).thenReturn(Arrays.asList(user));
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        // Act
        List<UserDTO> users = userService.getAllUsers();

        // Assert
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(userDTO, users.get(0));
        verify(userRepository).getAllUsers(UserRoleNameEnum.USER);
    }

    @Test
    public void testGetAllUsers_Unauthorized() {
        // Arrange
        when(jwtFilter.isAdmin()).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(BadCredentialsException.class, () -> {
            userService.getAllUsers();
        });
        assertEquals(RestaurantConstants.UNAUTHORIZED_ACCESS, exception.getMessage());
    }

    @Test
    public void testUpdate_Success() {
        // Arrange
        when(jwtFilter.isAdmin()).thenReturn(true);
        UserUpdateStatusDTO updateStatusDTO = new UserUpdateStatusDTO();
        updateStatusDTO.setId(1L);
        updateStatusDTO.setStatus(StatusNameEnum.ACTIVE);
        UserEntity user = new UserEntity();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.getAllAdmins(UserRoleNameEnum.ADMIN)).thenReturn(Arrays.asList("admin@example.com"));
        doNothing().when(emailUtils).sendSimpleMessage(any(), any(), any(), any());

        // Act
        String result = userService.update(updateStatusDTO);

        // Assert
        assertEquals(RestaurantConstants.USER_UPDATE_SUCCESS, result);
        verify(userRepository).updateStatus(StatusNameEnum.ACTIVE, 1L);
        verify(emailUtils).sendSimpleMessage(any(), eq("Account approved."), any(), any());
    }

    @Test
    public void testUpdate_UserNotFound() {
        // Arrange
        when(jwtFilter.isAdmin()).thenReturn(true);
        UserUpdateStatusDTO updateStatusDTO = new UserUpdateStatusDTO();
        updateStatusDTO.setId(1L);
        updateStatusDTO.setStatus(StatusNameEnum.ACTIVE);
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            userService.update(updateStatusDTO);
        });
        assertEquals(RestaurantConstants.USER_NOT_FOUND, exception.getMessage());
    }

    @Test
    public void testChangePassword_Success() {
        // Arrange
        String currentUser = "user@example.com";
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        UserChangePasswordDTO userChangePasswordDTO = new UserChangePasswordDTO();
        userChangePasswordDTO.setOldPassword(oldPassword);
        userChangePasswordDTO.setNewPassword(newPassword);

        UserEntity user = new UserEntity();
        user.setPassword("encodedOldPassword");

        when(jwtFilter.currentUser()).thenReturn(currentUser);
        when(userRepository.findByEmail(currentUser)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(oldPassword, user.getPassword())).thenReturn(true);
        when(passwordEncoder.encode(newPassword)).thenReturn("encodedNewPassword");
        when(userRepository.save(user)).thenReturn(user);

        // Act
        String result = userService.changePassword(userChangePasswordDTO);

        // Assert
        assertEquals(RestaurantConstants.PASSWORD_UPDATE_SUCCESS, result);
        verify(userRepository).save(user);
    }

    @Test
    public void testChangePassword_IncorrectOldPassword() {
        // Arrange
        String currentUser = "user@example.com";
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        UserChangePasswordDTO userChangePasswordDTO = new UserChangePasswordDTO();
        userChangePasswordDTO.setOldPassword(oldPassword);
        userChangePasswordDTO.setNewPassword(newPassword);

        UserEntity user = new UserEntity();
        user.setPassword("encodedOldPassword");

        when(jwtFilter.currentUser()).thenReturn(currentUser);
        when(userRepository.findByEmail(currentUser)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(oldPassword, user.getPassword())).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(BadCredentialsException.class, () -> {
            userService.changePassword(userChangePasswordDTO);
        });
        assertEquals(RestaurantConstants.INCORRECT_OLD_PASSWORD, exception.getMessage());
    }

    @Test
    public void testChangePassword_UserNotFound() {
        // Arrange
        String currentUser = "user@example.com";
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        UserChangePasswordDTO userChangePasswordDTO = new UserChangePasswordDTO();
        userChangePasswordDTO.setOldPassword(oldPassword);
        userChangePasswordDTO.setNewPassword(newPassword);

        when(jwtFilter.currentUser()).thenReturn(currentUser);
        when(userRepository.findByEmail(currentUser)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            userService.changePassword(userChangePasswordDTO);
        });
        assertEquals(RestaurantConstants.USER_NOT_FOUND, exception.getMessage());
    }
}
