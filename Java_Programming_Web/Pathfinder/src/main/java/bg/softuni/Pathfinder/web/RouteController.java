package bg.softuni.Pathfinder.web;

import bg.softuni.Pathfinder.model.enums.CategoryEnumType;
import bg.softuni.Pathfinder.model.enums.LevelEnumType;
import bg.softuni.Pathfinder.service.RouteService;
import bg.softuni.Pathfinder.service.dto.RouteShortInfoDTO;
import bg.softuni.Pathfinder.web.dto.UserRegisterDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RouteController {

    private RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/routes")
    public String routes(Model model) {
        List<RouteShortInfoDTO> routes = routeService.getAll();

        model.addAttribute("allRoutes", routes);

        return "routes";
    }

    @GetMapping("/add-route")
    public String addRoute(Model model) {
        if (!model.containsAttribute("route")) {
            model.addAttribute("route", new RouteShortInfoDTO());
        }

        model.addAttribute("levels", LevelEnumType.values());
        model.addAttribute("categoryTypes", CategoryEnumType.values());

        return "add-route";
    }
}
