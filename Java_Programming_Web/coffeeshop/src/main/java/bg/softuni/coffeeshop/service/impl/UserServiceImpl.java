package bg.softuni.coffeeshop.service.impl;


import bg.softuni.coffeeshop.model.dto.EmployeeDTO;
import bg.softuni.coffeeshop.model.dto.UserLoginDTO;
import bg.softuni.coffeeshop.model.dto.UserRegisterDTO;
import bg.softuni.coffeeshop.model.entity.User;
import bg.softuni.coffeeshop.repository.UserRepository;
import bg.softuni.coffeeshop.service.UserService;
import bg.softuni.coffeeshop.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }


    @Override
    public User registerUser(UserRegisterDTO userData, ModelMapper modelMapper) {
        User user = this.modelMapper.map(userData, User.class);
        return userRepository.save(user);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User byUsernameAndPassword = userRepository
                .findByUsernameAndPassword(username, password).orElse(null);

        if (byUsernameAndPassword == null ) {
            System.out.println("No such user found");
        }

        return byUsernameAndPassword;
    }

    @Override
    public void loginUser(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByUsername(userLoginDTO.getUsername());
        currentUser.login(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void logout() {
        currentUser.logout();
    }

    @Override
    public List<EmployeeDTO> findAllEmployeesAndCountOfOrdersOrderByCountDesc() {
        return userRepository
                .findAllByOrdersCountDescending()
                .stream()
                .map(employee -> {
                        EmployeeDTO employeeDTO = new EmployeeDTO();
                        employeeDTO.setUsername(employee.getUsername());
                        employeeDTO.setCountOfOrders(employee.getOrders().size());

                    return employeeDTO;

                })
                .collect(Collectors.toList());


    }

    @Override
    public boolean isLoggedIn() {
        return this.currentUser.getId() != null;
    }

    public Long getLoggedUserId() {
        return this.currentUser.getId();
    }
}
