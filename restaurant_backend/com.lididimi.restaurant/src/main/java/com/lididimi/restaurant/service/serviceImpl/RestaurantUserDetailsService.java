package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.model.entity.RoleEntity;
import com.lididimi.restaurant.model.entity.UserEntity;
import com.lididimi.restaurant.model.enums.UserRoleNameEnum;
import com.lididimi.restaurant.model.user.RestaurantUserDetails;
import com.lididimi.restaurant.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RestaurantUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private RestaurantUserDetails userEntityDetail;

    public RestaurantUserDetailsService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Fetching user details for email: {}", email);
        userEntityDetail = userRepository
                .findByEmail(email)
                .map(RestaurantUserDetailsService::mapToRestaurantUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found!"));


        return userRepository
                .findByEmail(email)
                .map(RestaurantUserDetailsService::mapToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found!"));
    }

    private static UserDetails mapToUserDetails(UserEntity userEntity) {

        return new RestaurantUserDetails(
                userEntity.getEmail(),
                userEntity.getName(),
                userEntity.getPassword(),
                userEntity.getStatus(),
                userEntity.getRoles().stream().map(RoleEntity::getName).map(RestaurantUserDetailsService::mapToGrantedAuth).toList()
        );
    }

    private static RestaurantUserDetails mapToRestaurantUserDetails(UserEntity userEntity) {

        return new RestaurantUserDetails(
                userEntity.getEmail(),
                userEntity.getName(),
                userEntity.getPassword(),
                userEntity.getStatus(),
                userEntity.getRoles().stream().map(RoleEntity::getName).map(RestaurantUserDetailsService::mapToGrantedAuth).toList()
        );
    }

    private static GrantedAuthority mapToGrantedAuth(UserRoleNameEnum role) {
        return new SimpleGrantedAuthority("ROLE_" + role.toString());
    }


    public RestaurantUserDetails getUserDetails() {
        return userEntityDetail;
    }
}





