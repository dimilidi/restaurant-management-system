package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.UserRegisterDTO;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean register(UserRegisterDTO data) {
        if(!data.getPassword().equals(data.getConfirmPassword())) {
            return false;
        }

        boolean isUsernameOrEmailTaken = userRepository.existsByUsernameOrEmail(data.getUsername(), data.getEmail());
        if(isUsernameOrEmailTaken) {
            return false;
        }

   /*    if(userRepository.findByEmail(data.getEmail()).isPresent()) {
            return false;
       }*/

        User mappedUser = modelMapper.map(data, User.class);
        mappedUser.setPassword(passwordEncoder.encode(data.getPassword()));

        userRepository.save(mappedUser);

        return true;
    }
}
