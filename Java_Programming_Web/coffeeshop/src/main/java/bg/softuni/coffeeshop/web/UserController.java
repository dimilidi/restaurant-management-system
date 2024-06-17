package bg.softuni.coffeeshop.web;

import bg.softuni.coffeeshop.model.dto.UserLoginDTO;
import bg.softuni.coffeeshop.model.dto.UserRegisterDTO;
//import bg.softuni.coffeeshop.model.service.UserServiceModel;
import bg.softuni.coffeeshop.model.entity.User;
import bg.softuni.coffeeshop.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @ModelAttribute("userRegisterDTO")
    public UserRegisterDTO userRegisterDTO() {
        return new UserRegisterDTO();
    }

    @ModelAttribute("userLoginDTO")
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterDTO userRegisterDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() ||
                !userRegisterDTO.getPassword()
                        .equals(userRegisterDTO.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);
            return "redirect:/users/register";
        }

        // UserServiceModel userData = modelMapper.map(userRegisterDTO, UserServiceModel.class);

        userService.registerUser(userRegisterDTO, modelMapper);


        return "redirect:/users/login";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginDTO userLoginDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);
            return "redirect:/users/login";
        }

        if (userService.findByUsernameAndPassword(userLoginDTO.getUsername(), userLoginDTO.getPassword()) == null) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("badCredentials", true);
            return "redirect:/users/login";
        }

        userService.loginUser(userLoginDTO);

        return "redirect:/home";


    }

    @GetMapping("/logout")
    public String logout() {
        userService.logout();

        return "redirect:/";
    }
/*    @PostMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();

        return "redirect:/";
    }*/




}
