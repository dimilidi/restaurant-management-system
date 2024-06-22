package bg.softuni.andreys.service.impl;

import bg.softuni.andreys.model.dto.UserLoginDTO;
import bg.softuni.andreys.model.dto.UserRegisterDTO;
import bg.softuni.andreys.model.entity.UserEntity;
import bg.softuni.andreys.repository.UserRepository;
import bg.softuni.andreys.service.UserService;
import bg.softuni.andreys.utils.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(UserRegisterDTO data) {
        if (!data.getPassword().equals(data.getConfirmPassword())) {
            return false;
        }

        boolean isUsernameOrEmailTaken = userRepository.existsByUsernameOrEmail(data.getUsername(), data.getEmail());
        if (isUsernameOrEmailTaken) {
            return false;
        }

        UserEntity mappedUser = modelMapper.map(data, UserEntity.class);
        mappedUser.setPassword(passwordEncoder.encode(data.getPassword()));

        userRepository.save(mappedUser);

        return true;
    }

    @Override
    public Optional<UserEntity> findByUsername (String username) {
        return userRepository.findByUsername(username);
    }
}
