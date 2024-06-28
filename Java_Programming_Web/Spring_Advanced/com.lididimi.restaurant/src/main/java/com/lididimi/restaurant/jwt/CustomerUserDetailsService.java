package com.lididimi.restaurant.jwt;

import com.lididimi.restaurant.model.entity.UserEntity;
import com.lididimi.restaurant.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private UserEntity userEntityDetail;

    public CustomerUserDetailsService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername {}", username);
         userEntityDetail = userRepository.findByEmail(username).orElse(null);
        if (userEntityDetail != null) {
             return new org.springframework.security.core.userdetails.User(
                    userEntityDetail.getEmail(),
                    userEntityDetail.getPassword(),
                    new ArrayList<>()
            );
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public UserEntity getUserDetails() {
        return userEntityDetail;
    }
}
