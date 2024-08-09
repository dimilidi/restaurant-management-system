package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.exception.user.AlreadyExistsException;
import com.lididimi.restaurant.exception.user.BadCredentialsException;
import com.lididimi.restaurant.model.dto.user.UserDTO;
import com.lididimi.restaurant.model.dto.user.UserLoginDTO;
import com.lididimi.restaurant.model.dto.user.UserRegisterDTO;
import com.lididimi.restaurant.model.entity.RoleEntity;
import com.lididimi.restaurant.model.entity.UserEntity;
import com.lididimi.restaurant.model.enums.StatusNameEnum;
import com.lididimi.restaurant.model.enums.UserRoleNameEnum;
import com.lididimi.restaurant.model.user.RestaurantUserDetails;
import com.lididimi.restaurant.repository.RoleRepository;
import com.lididimi.restaurant.repository.UserRepository;
import com.lididimi.restaurant.service.AuthService;
import com.lididimi.restaurant.service.JwtService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    public final RestaurantUserDetailsService restaurantUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;

    public AuthServiceImpl(
            UserRepository userRepository,
            ModelMapper modelMapper,
            AuthenticationManager authenticationManager,
            RestaurantUserDetailsService restaurantUserDetailsService,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository,
           JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.restaurantUserDetailsService = restaurantUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.jwtService = jwtService;
    }

    @Transactional
    @Override
    public UserDTO register(UserRegisterDTO userRegisterDTO) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(userRegisterDTO.getEmail());

        if (userOptional.isPresent()) {
            throw new AlreadyExistsException(RestaurantConstants.EMAIL_EXISTS);
        }

        Optional<RoleEntity> optionalRole = roleRepository.findByName(UserRoleNameEnum.USER);
        UserEntity userEntity = modelMapper.map(userRegisterDTO, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        userEntity.setStatus(StatusNameEnum.INACTIVE);

        if (optionalRole.isPresent()) {
            RoleEntity userRole = optionalRole.get();
            userEntity.setRoles(List.of(userRole));
            roleRepository.save(userRole);
        }

        userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserDTO.class);
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        try {
            Authentication authentication = performAuthentication(userLoginDTO);
            if (authentication.isAuthenticated()) {
                RestaurantUserDetails currentUser = restaurantUserDetailsService.getUserDetails();
                return processAuthenticatedUser(currentUser);
            } else {
                log.error("Authentication Failed");
                throw new BadCredentialsException(RestaurantConstants.BAD_CREDENTIALS);
            }
        } catch (BadCredentialsException e) {
            log.error("Invalid credentials: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error during authentication: {}", e.getMessage());
            throw new BadCredentialsException(RestaurantConstants.BAD_CREDENTIALS);
        }
    }

    private Authentication performAuthentication(UserLoginDTO userLoginDTO) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword())
        );
    }

    private String processAuthenticatedUser(RestaurantUserDetails currentUser) {
        if (StatusNameEnum.ACTIVE.equals(currentUser.getStatus())) {
            log.info("Active User");
            log.info(currentUser.toString());

            List<String> roles = currentUser.getRoles();
            log.info("Authorities {}", currentUser.getAuthorities().stream().toList());

            return jwtService.generateToken(currentUser.getUsername(), currentUser.getName(), roles);
        } else {
            log.info("Wait for admin approval");
            throw new BadCredentialsException(RestaurantConstants.UNAPPROVED);
        }
    }

}