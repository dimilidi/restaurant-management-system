package bg.softuni.shoppinglist.service.impl;

import bg.softuni.shoppinglist.model.dto.UserLoginDTO;
import bg.softuni.shoppinglist.model.dto.UserRegisterDTO;
import bg.softuni.shoppinglist.model.entity.UserEntity;
import bg.softuni.shoppinglist.repository.UserRepository;
import bg.softuni.shoppinglist.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean register(UserRegisterDTO registerData) {
        try {
            userRepository.save(modelMapper.map(registerData, UserEntity.class));
        } catch (Exception e) {
            return false;
        }

        return  true;
    }

    @Override
    public void login(UserLoginDTO loginData) {


    }

    @Override
    public UserEntity findByUsernameAndPassword(String username, String password) {
        return userRepository.
                findByUsernameAndPassword(username, password)
                .orElse(null);
    }
}
