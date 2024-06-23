package bg.softuni.coffeeshop.web;

import bg.softuni.coffeeshop.model.dto.OrderDTO;
import bg.softuni.coffeeshop.service.OrderService;
import bg.softuni.coffeeshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final UserService userService;
    private final OrderService orderService;

    public HomeController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/home")
    public String index(Model model) {

        if(!userService.isLoggedIn()) {
            return "index";
        }

        List<OrderDTO> orders = orderService.findAllOrderOrdersByPriceDesc();

        model.addAttribute("orders", orders);
        model.addAttribute("totalTime", orders
                .stream()
                .map(orderDTO -> orderDTO.getCategory().getNeededTime())
                .reduce(Integer::sum)
                .orElse(null)
        );
        model.addAttribute("employees", userService.findAllEmployeesAndCountOfOrdersOrderByCountDesc());


        return "home";
    }

}
