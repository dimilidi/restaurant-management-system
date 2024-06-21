package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.UserLoginDTO;
import com.dictionaryapp.model.dto.UserRegisterDTO;
import com.dictionaryapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerData")
    public UserRegisterDTO initRegisterData() {
        return new UserRegisterDTO();
    }

    @ModelAttribute("loginData")
    public UserLoginDTO initLoginData() {
        return new UserLoginDTO();
    }

    @GetMapping("/register")
    public String register() {
        if (userService.isLoggedIn()) {
            return "redirect:/home";
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterDTO data,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (userService.isLoggedIn()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);

            return "redirect:/register";
        }

        boolean success = userService.register(data);

        return "redirect:/login";
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

            return "redirect:/login";
        }

        boolean success = userService.login(data);

        if (!success) {
            redirectAttributes.addFlashAttribute("loginData", data);
            redirectAttributes.addFlashAttribute("badCredentials", true);

            return "redirect:/login";
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
