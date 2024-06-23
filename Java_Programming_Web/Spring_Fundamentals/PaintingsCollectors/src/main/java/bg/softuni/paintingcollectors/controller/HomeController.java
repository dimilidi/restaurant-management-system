package bg.softuni.paintingcollectors.controller;

import bg.softuni.paintingcollectors.model.dto.painting.PaintingHomeDTO;
import bg.softuni.paintingcollectors.service.PaintingService;
import bg.softuni.paintingcollectors.service.UserService;
import bg.softuni.paintingcollectors.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {


    private final UserService userService;
    private final CurrentUser currentUser;
    private final PaintingService paintingService;

    public HomeController(UserService userService, CurrentUser currentUser, PaintingService paintingService) {
        this.userService = userService;
        this.currentUser = currentUser;
        this.paintingService = paintingService;
    }

    @GetMapping("/")
    public String notLogged() {
        if (userService.isLoggedIn()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public ModelAndView home() {
        if (!currentUser.isLoggedIn()) {
            return new ModelAndView("redirect:/login");
        }

        ModelAndView modelAndView = new ModelAndView("home");
        PaintingHomeDTO paintingsForHomePage = paintingService.getPaintingsForHomePage();
        modelAndView.addObject("paintingHomeDTO", paintingsForHomePage);

        modelAndView.setViewName("home");

        return modelAndView;
    }
}
