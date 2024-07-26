package com.lididimi.restaurant.jwt;

import com.lididimi.restaurant.model.entity.UserEntity;
import com.lididimi.restaurant.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    private UserEntity userEntityDetail;

    public RestaurantUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Fetching user details for email: {}", email);
        userEntityDetail = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<GrantedAuthority> grantedAuthorities = userEntityDetail.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                userEntityDetail.getEmail(),
                userEntityDetail.getPassword(),
                grantedAuthorities
        );
    }

    public UserEntity getUserDetails() {
        return userEntityDetail;
    }
}


