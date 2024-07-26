package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.exception.common.ObjectNotFoundException;
import com.lididimi.restaurant.exception.user.AlreadyExistsException;
import com.lididimi.restaurant.exception.user.BadCredentialsException;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.jwt.JwtUtils;
import com.lididimi.restaurant.jwt.RestaurantUserDetailsService;
import com.lididimi.restaurant.model.dto.*;
import com.lididimi.restaurant.model.entity.PasswordResetToken;
import com.lididimi.restaurant.model.entity.RoleEntity;
import com.lididimi.restaurant.model.entity.UserEntity;
import com.lididimi.restaurant.model.enums.StatusNameEnum;
import com.lididimi.restaurant.model.enums.UserRoleNameEnum;
import com.lididimi.restaurant.repository.PasswordResetTokenRepository;
import com.lididimi.restaurant.repository.RoleRepository;
import com.lididimi.restaurant.repository.UserRepository;
import com.lididimi.restaurant.service.AuthService;
import com.lididimi.restaurant.service.UserService;
import com.lididimi.restaurant.utils.EmailUtils;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    public final RestaurantUserDetailsService restaurantUserDetailsService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final JwtFilter jwtFilter;
    private final EmailUtils emailUtils;
    private final PasswordResetTokenRepository tokenRepository;
    private final RoleRepository roleRepository;
    private final HttpServletRequest request;

    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper, AuthenticationManager authenticationManager, RestaurantUserDetailsService restaurantUserDetailsService, JwtUtils jwtUtils, PasswordEncoder passwordEncoder, JwtFilter jwtFilter, EmailUtils emailUtils, PasswordResetTokenRepository tokenRepository, RoleRepository roleRepository, HttpServletRequest request) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.restaurantUserDetailsService = restaurantUserDetailsService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.jwtFilter = jwtFilter;
        this.emailUtils = emailUtils;
        this.tokenRepository = tokenRepository;
        this.roleRepository = roleRepository;
        this.request = request;
    }

    @Transactional
    @Override
    public UserDTO register(UserRegisterDTO userRegisterDTO) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(userRegisterDTO.getEmail());

        if (userOptional.isPresent()) {
            throw new AlreadyExistsException(RestaurantConstants.EMAIL_EXISTS);
        }

        Optional<RoleEntity> optionalRole = roleRepository.findByName(UserRoleNameEnum.ADMIN);
        UserEntity userEntity = modelMapper.map(userRegisterDTO, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        userEntity.setStatus(StatusNameEnum.ACTIVE);

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
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword())
            );

            if (auth.isAuthenticated()) {
                log.info("Authentication Success");
                UserEntity userEntity = restaurantUserDetailsService.getUserDetails();
                if (StatusNameEnum.ACTIVE.equals(userEntity.getStatus())) {
                    log.info("Active User");
                    List<String> roles = userEntity.getRoles().stream()
                            .map(roleEntity -> roleEntity.getName().name())
                            .collect(Collectors.toList());

                    String token = jwtUtils.generateToken(userEntity.getEmail(), userEntity.getName(), roles);

                    return token;
                } else {
                    log.info("Wait for admin approval");
                    throw new BadCredentialsException(RestaurantConstants.UNAPPROVED);
                }
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

}


