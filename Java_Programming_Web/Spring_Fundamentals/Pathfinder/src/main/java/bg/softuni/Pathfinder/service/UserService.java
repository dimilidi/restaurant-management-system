package bg.softuni.Pathfinder.service;

import bg.softuni.Pathfinder.data.RoleRepository;
import bg.softuni.Pathfinder.data.UserRepository;
import bg.softuni.Pathfinder.model.Role;
import bg.softuni.Pathfinder.model.User;
import bg.softuni.Pathfinder.model.enums.LevelEnumType;
import bg.softuni.Pathfinder.model.enums.UserRolesEnum;
import bg.softuni.Pathfinder.service.dto.UserProfileDTO;
import bg.softuni.Pathfinder.web.dto.UserLoginDTO;
import bg.softuni.Pathfinder.web.dto.UserRegisterDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final CurrentUser  currentUser;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, CurrentUser currentUser, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.roleRepository = roleRepository;
    }


    public void register(UserRegisterDTO userRegisterDTO) {
        Role role = new Role();
        role.setName(UserRolesEnum.USER);
        roleRepository.save(role);

        User user = this.modelMapper.map(userRegisterDTO, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setLevel(LevelEnumType.BEGINNER);
        user.setRoles(Set.of(role));

        userRepository.save(user);
    }

    public void login(UserLoginDTO userLoginDTO) {
        User user =  userRepository.findByUsername(userLoginDTO.getUsername());

        if (user == null) {
            // throw error
            return;
        }

        String rawPassword = userLoginDTO.getPassword();
        String encodedPassword = user.getPassword();


        if (encodedPassword != null && passwordEncoder.matches(rawPassword, encodedPassword)) {
            currentUser.setLogged(true);
            currentUser.setUsername(user.getUsername());
            currentUser.setAdmin(user.getRoles().contains(UserRolesEnum.ADMIN));
            if (user.getRoles().contains(UserRolesEnum.ADMIN)) {
                currentUser.setAdmin(true);
            }
        }
    }

    public void logout() {
        currentUser.logout();
    }


    public UserProfileDTO getProfile(String username) {

        final User byUsername = this.userRepository.findByUsername(username);

        return this.modelMapper.map(byUsername, UserProfileDTO.class);
    }
}
