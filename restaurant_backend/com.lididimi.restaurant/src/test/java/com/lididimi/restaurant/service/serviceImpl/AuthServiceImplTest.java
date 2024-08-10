package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.exception.user.AlreadyExistsException;
import com.lididimi.restaurant.exception.user.BadCredentialsException;
import com.lididimi.restaurant.model.user.RestaurantUserDetails;
import com.lididimi.restaurant.model.dto.user.UserDTO;
import com.lididimi.restaurant.model.dto.user.UserLoginDTO;
import com.lididimi.restaurant.model.dto.user.UserRegisterDTO;
import com.lididimi.restaurant.model.entity.RoleEntity;
import com.lididimi.restaurant.model.entity.UserEntity;
import com.lididimi.restaurant.model.enums.StatusNameEnum;
import com.lididimi.restaurant.model.enums.UserRoleNameEnum;
import com.lididimi.restaurant.repository.PasswordResetTokenRepository;
import com.lididimi.restaurant.repository.RoleRepository;
import com.lididimi.restaurant.repository.UserRepository;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    private JwtServiceImpl jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmailServiceImpl emailUtils;

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

    // Helper method for common setup
    private RestaurantUserDetails commonSetup(String email, StatusNameEnum status) {
        // Mock authentication
        Authentication auth = mock(Authentication.class);

        // Mock user details with specified status
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        RestaurantUserDetails restaurantUserDetails = new RestaurantUserDetails(
                email,
                "Test User",
                "password",
                status,
                authorities
        );

        // Mocking Spring Security components
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        when(auth.isAuthenticated()).thenReturn(true);
        when(restaurantUserDetailsService.getUserDetails()).thenReturn(restaurantUserDetails);

        return restaurantUserDetails;
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
        verify(roleRepository, times(1)).findByName(UserRoleNameEnum.USER);
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
        // Use common setup
        RestaurantUserDetails restaurantUserDetails = commonSetup("test@example.com", StatusNameEnum.ACTIVE);

        // Mock JWT token generation
        when(jwtService.generateToken(anyString(), anyString(), anyList())).thenReturn("token");

        // Given
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("test@example.com");
        userLoginDTO.setPassword("password");

        // When
        String token = authService.login(userLoginDTO);

        // Then
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService, times(1)).generateToken(anyString(), anyString(), anyList());
        assertEquals("token", token);
    }

    @Test
    public void testLogin_UserNotActive() {
        // Use common setup with inactive user
        commonSetup("test@example.com", StatusNameEnum.INACTIVE);

        // Given
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("test@example.com");
        userLoginDTO.setPassword("password");

        // When & Then
        assertThrows(BadCredentialsException.class, () -> authService.login(userLoginDTO));
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(restaurantUserDetailsService, times(1)).getUserDetails();
    }

    @Test
    public void testLogin_AuthenticationFailed() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("test@example.com");
        userLoginDTO.setPassword("wrongpassword");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        // When & Then
        BadCredentialsException thrownException = assertThrows(BadCredentialsException.class, () -> authService.login(userLoginDTO));
        assertEquals("Invalid credentials", thrownException.getMessage());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    public void testLogin_AuthenticationFailsWithoutThrowingException() {
        // Given
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("test@example.com");
        userLoginDTO.setPassword("password");

        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(false);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        // When & Then
        BadCredentialsException thrownException = assertThrows(BadCredentialsException.class, () -> authService.login(userLoginDTO));
        assertEquals(RestaurantConstants.BAD_CREDENTIALS, thrownException.getMessage());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(restaurantUserDetailsService, never()).getUserDetails(); // Ensure user details are not fetched
    }

    @Test
    public void testLogin_UnexpectedError() {
        // Given
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("test@example.com");
        userLoginDTO.setPassword("password");

        // Simulate unexpected exception during authentication
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("Unexpected error"));

        // When & Then
        BadCredentialsException thrownException = assertThrows(BadCredentialsException.class, () -> authService.login(userLoginDTO));
        assertEquals(RestaurantConstants.BAD_CREDENTIALS, thrownException.getMessage());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }
}
