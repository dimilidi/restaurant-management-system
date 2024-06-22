package bg.softuni.andreys.web;

import bg.softuni.andreys.model.dto.UserLoginDTO;
import bg.softuni.andreys.model.dto.UserRegisterDTO;
import bg.softuni.andreys.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final HttpSession httpSession;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, HttpSession httpSession, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.httpSession = httpSession;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/register")
    public String register(Model model) {
        if(httpSession.getAttribute("user") != null) {
            return "redirect:/";
        }

        if (!model.containsAttribute("registerData")) {
            model.addAttribute("registerData", new UserRegisterDTO());

        }
        return "register";
    }


    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDTO data,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if(httpSession.getAttribute("user") != null) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);
            return "redirect:register";
        }

        boolean success = userService.register(data);

        return "redirect:login";
    }



    @GetMapping("/login")
    public String login(Model model) {
        if(httpSession.getAttribute("user") != null) {
            return "redirect:/";
        }

        if (!model.containsAttribute("loginData")) {
            model.addAttribute("loginData", new UserLoginDTO());
            model.addAttribute("badCredentials", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@Valid UserLoginDTO data,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginData", bindingResult);
            return "redirect:login";
        }

        boolean isExists = userService.findByUsername(data.getUsername())
                .filter(user -> passwordEncoder.matches(data.getPassword(), user.getPassword())).isPresent();


        if (!isExists) {
            redirectAttributes.addFlashAttribute("loginData", data);
            redirectAttributes.addFlashAttribute("badCredentials", true);
            return "redirect:login";
        }

        httpSession.setAttribute("user", data);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        if(httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }
        httpSession.invalidate();
        return "redirect:/";
    }
}
