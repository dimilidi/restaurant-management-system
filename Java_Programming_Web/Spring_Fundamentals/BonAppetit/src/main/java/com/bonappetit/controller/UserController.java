package com.bonappetit.controller;

import com.bonappetit.model.dto.UserLoginDTO;
import com.bonappetit.model.dto.UserRegisterDTO;
import com.bonappetit.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerData")
    public UserRegisterDTO registerDTO() {
        return new UserRegisterDTO();
    }

    @ModelAttribute("loginData")
    public UserLoginDTO initLoginData() {
        return new UserLoginDTO();
    }

    @GetMapping("/register")
    public String register(){
        if (userService.isLoggedIn()) {
            return "redirect:/home";
        }

        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDTO data,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes
                             ){
        if (userService.isLoggedIn()) {
            return "redirect:/home";
        }

        if(bindingResult.hasErrors() || !data.getPassword().equals(data.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);
            return "redirect:register";

        }

        boolean isSuccess = userService.register(data);

        if(!isSuccess) {
            return "redirect:/users/register";
        }


        return "redirect:/users/login";
    }


    @GetMapping("/login")
    public String login() {
        if (userService.isLoggedIn()) {
            return "redirect:/home";
        }

        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginDTO data,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (userService.isLoggedIn()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginData", bindingResult);

            return "redirect:/users/login";
        }

        boolean success = userService.login(data);

        if (!success) {
            redirectAttributes.addFlashAttribute("loginData", data);
            redirectAttributes.addFlashAttribute("loginError", true);

            return "redirect:/users/login";
        }

        return "redirect:/home";
    }

    @DeleteMapping("/logout")
    public String logout() {
        if (!userService.isLoggedIn()) {
            return "redirect:/";
        }

        userService.logout();
        return "redirect:/";
    }
}
