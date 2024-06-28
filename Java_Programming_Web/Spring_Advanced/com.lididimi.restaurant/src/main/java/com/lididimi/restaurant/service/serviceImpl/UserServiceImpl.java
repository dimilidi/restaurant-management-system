package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.jwt.CustomerUserDetailsService;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.jwt.JwtUtils;
import com.lididimi.restaurant.model.entity.User;
import com.lididimi.restaurant.repository.UserRepository;
import com.lididimi.restaurant.service.UserService;
import com.lididimi.restaurant.utils.EmailUtils;
import com.lididimi.restaurant.utils.RestaurantUtils;
import com.lididimi.restaurant.wrapper.UserWrapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final CustomerUserDetailsService customerUserDetailsService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final JwtFilter jwtFilter;
    private final EmailUtils emailUtils;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, AuthenticationManager authenticationManager, CustomerUserDetailsService customerUserDetailsService, JwtUtils jwtUtils, PasswordEncoder passwordEncoder, JwtFilter jwtFilter, EmailUtils emailUtils) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.customerUserDetailsService = customerUserDetailsService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.jwtFilter = jwtFilter;
        this.emailUtils = emailUtils;
    }

    @Override
    public ResponseEntity<String> register(Map<String, String> requestMap) {
        log.info("Inside register {}", requestMap);
        try {
            if (validateRegister(requestMap)) {
                Optional<User> userOptional = userRepository.findByEmail(requestMap.get("email"));
                if (userOptional.isEmpty()) {
                    User user = modelMapper.map(requestMap, User.class);
                    user.setPassword(passwordEncoder.encode(requestMap.get("password")));
                    user.setStatus("false");
                    user.setRole("user");
                    userRepository.save(user);
                    return RestaurantUtils.getResponseEntity("Successfully registered", HttpStatus.OK);
                } else {
                    return RestaurantUtils.getResponseEntity("Email already exists", HttpStatus.BAD_REQUEST);
                }
            } else {
                return RestaurantUtils.getResponseEntity(RestaurantConstants.UNAUTHORIZED_ACCESS, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateRegister(Map<String, String> requestMap) {
        if (requestMap.containsKey("name")
                && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("password")
                && requestMap.containsKey("email")) {
            return true;
        }
        return false;
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside login");
        try {
            // Attempt authentication
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
            );

            // Check if authenticated
            if (auth.isAuthenticated()) {
                // Check if user is approved by admin
                if (customerUserDetailsService.getUserDetails().getStatus().equalsIgnoreCase("true")) {
                    String token = jwtUtils.generateToken(customerUserDetailsService.getUserDetails().getEmail(),
                            customerUserDetailsService.getUserDetails().getRole());
                    return new ResponseEntity<>(token, HttpStatus.OK);
                } else {
                    // User is not approved by admin
                    log.info("Wait for admin approval");
                    return new ResponseEntity<>("Wait for admin approval", HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception e) {
            // Authentication failed
            log.error("{}", e);
        }
        // Return bad credentials if any exception occurred or authentication failed
        return new ResponseEntity<>("Bad Credentials.", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUsers() {
        try{
            if (jwtFilter.isAdmin()) {
                return new ResponseEntity<>(userRepository.getAllUsers(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
            }

        }catch (Exception e){

        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try {
            if(jwtFilter.isAdmin()) {
                Optional<User> optionalUser = userRepository.findById(Long.parseLong(requestMap.get("id")));
                if(optionalUser.isPresent()) {
                    userRepository.updateStatus(requestMap.get("status"), Long.parseLong(requestMap.get("id")));
                    sendMailToAllAdmins(requestMap.get("status"), optionalUser.get().getEmail(), userRepository.getAllAdmins());
                    return RestaurantUtils.getResponseEntity("Successfully updated user status", HttpStatus.OK);
                } else {
                    return RestaurantUtils.getResponseEntity("User does not exist", HttpStatus.OK);
                }
            }else {
                return new ResponseEntity<>("Bad Credentials.", HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void sendMailToAllAdmins(String status, String user, List<String> allAdmins) {
        allAdmins.remove(jwtFilter.currentUser());

        if(status != null &&  status.equalsIgnoreCase("true")) {
            emailUtils.sendSimpleMessage(jwtFilter.currentUser(), "Account approved.", "User: " + user + "\nis approved by\nAdmin: " + jwtFilter.currentUser(), allAdmins);
        } else {
            emailUtils.sendSimpleMessage(jwtFilter.currentUser(), "Account disabled.", "User: " + user + "\nis disabled by\nAdmin: " + jwtFilter.currentUser(), allAdmins);

        }
    }
}
