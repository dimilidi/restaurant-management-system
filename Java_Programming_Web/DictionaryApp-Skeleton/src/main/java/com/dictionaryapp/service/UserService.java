package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.UserLoginDTO;
import com.dictionaryapp.model.dto.UserRegisterDTO;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    public boolean register(UserRegisterDTO data) {
        if (!data.getPassword().equals(data.getConfirmPassword())) {
            return false;
        }

        boolean isUsernameOrEmailTaken = userRepository.existsByUsernameOrEmail(data.getUsername(), data.getEmail());
        if (isUsernameOrEmailTaken) {
            return false;
        }

        User mappedUser = modelMapper.map(data, User.class);
        mappedUser.setPassword(passwordEncoder.encode(data.getPassword()));

        userRepository.save(mappedUser);

        return true;
    }

    public boolean login(UserLoginDTO data) {
        return userRepository.findByUsername(data.getUsername())
                .filter(user -> passwordEncoder.matches(data.getPassword(), user.getPassword()))
                .map(user -> {
                    currentUser.login(user);
                    return true;
                })
                .orElse(false);

        /*Optional<User> user = userRepository.findByUsername(data.getUsername());
        if(user.isEmpty()) {
            return false;
        }

        String rawPassword = data.getPassword();
        String encodedPassword = user.get().getPassword();


        if (encodedPassword != null && passwordEncoder.matches(rawPassword, encodedPassword)) {
            currentUser.login(user.get());

            return true;
        }

        //logoutUser();

        return false;*/
    }

}

