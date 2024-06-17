package bg.softuni.coffeeshop.service;

import bg.softuni.coffeeshop.model.dto.EmployeeDTO;
import bg.softuni.coffeeshop.model.dto.UserLoginDTO;
import bg.softuni.coffeeshop.model.dto.UserRegisterDTO;
import bg.softuni.coffeeshop.model.entity.User;
import org.modelmapper.ModelMapper;

import java.util.List;
//import bg.softuni.coffeeshop.model.service.UserServiceModel;

public interface UserService {
    User registerUser(UserRegisterDTO userData, ModelMapper modelMapper);


    User findByUsernameAndPassword(String username, String password);

    void loginUser(UserLoginDTO userLoginDTO);

    User findById(Long id);

    boolean isLoggedIn();

    void logout();

    List<EmployeeDTO> findAllEmployeesAndCountOfOrdersOrderByCountDesc();
}
