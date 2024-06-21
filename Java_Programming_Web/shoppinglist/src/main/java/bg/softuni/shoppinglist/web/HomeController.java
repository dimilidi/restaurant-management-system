package bg.softuni.shoppinglist.web;

import bg.softuni.shoppinglist.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public  String index(HttpSession httpSession, Model model) {

        if(httpSession.getAttribute("user") == null) {
            return "index";
        }

        model.addAttribute("totalSum", productService.getTotalSum());
        model.addAttribute("drinks", productService.getTotalSum());
        model.addAttribute("food", productService.getTotalSum());
        model.addAttribute("household", productService.getTotalSum());
        model.addAttribute("other", productService.getTotalSum());

        return "home";
    }
}
