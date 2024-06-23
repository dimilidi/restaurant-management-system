package bg.softuni.andreys.web;

import bg.softuni.andreys.service.ItemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final ItemService itemService;

    public HomeController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public ModelAndView index(HttpSession httpSession, ModelAndView modelAndView) {

        if(httpSession.getAttribute("user") == null) {
             modelAndView.setViewName("index");
        } else {
            modelAndView.addObject("items", itemService.findAllItems());
            modelAndView.setViewName("home");
        }

        return modelAndView;
    }

}
