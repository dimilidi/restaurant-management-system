package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.UserRegisterDTO;
import com.dictionaryapp.repo.UserRepository;
import jakarta.validation.constraints.Email;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(UserRegisterDTO data) {
        if(!data.getPassword().equals(data.getConfirmPassword())) {
            return false;
        }

        return true;

    }
}
