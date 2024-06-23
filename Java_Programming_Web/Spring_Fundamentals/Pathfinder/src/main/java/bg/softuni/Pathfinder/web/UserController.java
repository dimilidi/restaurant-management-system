package bg.softuni.Pathfinder.web;

import bg.softuni.Pathfinder.service.CurrentUser;
import bg.softuni.Pathfinder.service.UserService;
import bg.softuni.Pathfinder.web.dto.UserLoginDTO;
import bg.softuni.Pathfinder.web.dto.UserRegisterDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final CurrentUser currentUser;

    public UserController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/register")
    public String viewRegister(Model model) {
        if (!model.containsAttribute("registerData")) {
            model.addAttribute("registerData", new UserRegisterDTO());
        }
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@ModelAttribute("registerData") @Valid UserRegisterDTO registerData,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerData", registerData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);

            return "redirect:/users/register";
        }

        userService.register(registerData);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String viewLogin(Model model) {

        if(currentUser.isLogged()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("loginData")) {
            model.addAttribute("loginData", new UserLoginDTO());
        };
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@Valid UserLoginDTO loginData,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes
                          ) {
        if(currentUser.isLogged()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginData", loginData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginData", bindingResult);

            return "redirect:/users/login";
        }

        userService.login(loginData);

        return "redirect:/";
    }

    @DeleteMapping("/logout")
    public String logout() {
        userService.logout();

        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("profileData", userService.getProfile(currentUser.getUsername()));

        return "profile";
    }

}
