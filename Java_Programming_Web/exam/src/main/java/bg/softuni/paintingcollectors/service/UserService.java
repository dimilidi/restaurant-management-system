package bg.softuni.paintingcollectors.service;


import bg.softuni.paintingcollectors.model.dto.user.UserLoginDTO;
import bg.softuni.paintingcollectors.model.dto.user.UserRegisterDTO;
import bg.softuni.paintingcollectors.model.entity.UserEntity;
import bg.softuni.paintingcollectors.repository.UserRepository;
import bg.softuni.paintingcollectors.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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

        UserEntity mappedUser = modelMapper.map(data, UserEntity.class);
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
    }

    public void logout() {
        currentUser.logout();
    }

    public boolean isLoggedIn() {
        return this.currentUser.id() != null;
    }

    public UserEntity findByUsername(String username) {

        return this.userRepository
                .findByUsername(username)
                .get();
    }



}

