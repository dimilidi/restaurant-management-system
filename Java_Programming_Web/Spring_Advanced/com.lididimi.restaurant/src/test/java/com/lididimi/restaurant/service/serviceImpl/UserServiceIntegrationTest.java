package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.model.dto.UserDTO;
import com.lididimi.restaurant.model.dto.UserRegisterDTO;
import com.lididimi.restaurant.model.entity.UserEntity;
import com.lididimi.restaurant.repository.RoleRepository;
import com.lididimi.restaurant.repository.UserRepository;
import com.lididimi.restaurant.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Transactional
    public void testRegisterUser() {
        // Arrange
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setEmail("testuser@example.com");
        userRegisterDTO.setPassword("password123");
        userRegisterDTO.setName("Test User");

        // Act
        UserDTO registeredUser = userService.register(userRegisterDTO);

        // Assert
        assertNotNull(registeredUser);
        assertEquals("testuser@example.com", registeredUser.getEmail());

        UserEntity userEntity = userRepository.findByEmail("testuser@example.com").orElse(null);
        assertNotNull(userEntity);
        assertTrue(passwordEncoder.matches("password123", userEntity.getPassword()));
    }
}
