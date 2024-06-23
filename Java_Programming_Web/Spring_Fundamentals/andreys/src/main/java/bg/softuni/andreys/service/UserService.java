package bg.softuni.andreys.service;

import bg.softuni.andreys.model.dto.UserLoginDTO;
import bg.softuni.andreys.model.dto.UserRegisterDTO;
import bg.softuni.andreys.model.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    boolean register(UserRegisterDTO data);

    Optional<UserEntity> findByUsername (String username);
}
