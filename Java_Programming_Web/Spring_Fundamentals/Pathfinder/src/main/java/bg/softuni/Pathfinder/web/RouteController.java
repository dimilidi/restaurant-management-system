package bg.softuni.Pathfinder.web;

import bg.softuni.Pathfinder.model.enums.CategoryEnumType;
import bg.softuni.Pathfinder.model.enums.LevelEnumType;
import bg.softuni.Pathfinder.service.RouteService;
import bg.softuni.Pathfinder.service.dto.RouteShortInfoDTO;
import bg.softuni.Pathfinder.web.dto.AddRouteDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class RouteController {

    private RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @ModelAttribute("routeData")
    public AddRouteDTO routeData() {
        return new AddRouteDTO();
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

    @PostMapping("/add-route")
    public String doAddRoute(@Valid AddRouteDTO data,
                             @RequestParam("gpxCoordinates") MultipartFile file,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes
                             ) throws IOException {

        // validate errors

        routeService.add(data, file);
        return "redirect:/add-route";
    }
}
