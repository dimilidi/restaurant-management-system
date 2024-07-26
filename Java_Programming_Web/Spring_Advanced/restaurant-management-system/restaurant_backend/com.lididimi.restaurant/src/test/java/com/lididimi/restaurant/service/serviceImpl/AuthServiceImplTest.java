package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.exception.user.AlreadyExistsException;
import com.lididimi.restaurant.exception.user.BadCredentialsException;
import com.lididimi.restaurant.jwt.JwtUtils;
import com.lididimi.restaurant.jwt.RestaurantUserDetailsService;
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
import com.lididimi.restaurant.utils.EmailUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authService;

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
    private EmailUtils emailUtils;

    @Mock
    private PasswordResetTokenRepository tokenRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister_Success() {
        // Given
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setEmail("test@example.com");
        userRegisterDTO.setPassword("password");

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@example.com");

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(UserRoleNameEnum.ADMIN);

        // When
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(roleRepository.findByName(UserRoleNameEnum.ADMIN)).thenReturn(Optional.of(roleEntity));
        when(modelMapper.map(any(UserRegisterDTO.class), eq(UserEntity.class))).thenReturn(userEntity);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserDTO expectedUserDTO = new UserDTO();
        expectedUserDTO.setEmail("test@example.com");
        when(modelMapper.map(any(UserEntity.class), eq(UserDTO.class))).thenReturn(expectedUserDTO);

        // When
        UserDTO userDTO = authService.register(userRegisterDTO);

        // Then
        verify(userRepository, times(1)).findByEmail(anyString());
        verify(roleRepository, times(1)).findByName(UserRoleNameEnum.ADMIN);
        verify(userRepository, times(1)).save(any(UserEntity.class));
        assertEquals("test@example.com", userDTO.getEmail()); // Ensure email matches
    }


    @Test
    public void testRegister_UserAlreadyExists() {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setEmail("test@example.com");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new UserEntity()));

        assertThrows(AlreadyExistsException.class, () -> authService.register(userRegisterDTO));
    }

    @Test
    public void testLogin_Success() {
        // Given
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("test@example.com");
        userLoginDTO.setPassword("password");

        // Mock authentication
        Authentication auth = mock(Authentication.class);

        // Mock user entity
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@example.com");
        userEntity.setName("Test User");
        userEntity.setStatus(StatusNameEnum.ACTIVE);

        // Mock role entity
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(UserRoleNameEnum.ADMIN); // Ensure this is set properly

        userEntity.setRoles(List.of(roleEntity));

        // Mocking Spring Security components
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        when(auth.isAuthenticated()).thenReturn(true);
        when(restaurantUserDetailsService.getUserDetails()).thenReturn(userEntity);

        // Mock JWT token generation
        when(jwtUtils.generateToken(anyString(), anyString(), anyList())).thenReturn("token");

        // When
        String token = authService.login(userLoginDTO);

        // Then
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        assertEquals("token", token); // Ensure token matches the expected value
    }


    @Test
    public void testLogin_UserNotActive() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("test@example.com");
        userLoginDTO.setPassword("password");

        Authentication auth = mock(Authentication.class);
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@example.com");
        userEntity.setStatus(StatusNameEnum.INACTIVE);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        when(auth.isAuthenticated()).thenReturn(true);
        when(restaurantUserDetailsService.getUserDetails()).thenReturn(userEntity);

        assertThrows(BadCredentialsException.class, () -> authService.login(userLoginDTO));
    }

    @Test
    public void testLogin_AuthenticationFailed() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("test@example.com");
        userLoginDTO.setPassword("wrongpassword");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        assertThrows(BadCredentialsException.class, () -> authService.login(userLoginDTO));
    }
}

