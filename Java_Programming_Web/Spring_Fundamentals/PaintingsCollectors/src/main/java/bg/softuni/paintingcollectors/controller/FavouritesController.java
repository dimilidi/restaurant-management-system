package bg.softuni.paintingcollectors.controller;

import bg.softuni.paintingcollectors.service.PaintingService;
import bg.softuni.paintingcollectors.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/favourites")
public class FavouritesController {
    private final PaintingService paintingService;
    private final CurrentUser currentUser;

    public FavouritesController(PaintingService paintingService, CurrentUser currentUser) {
        this.paintingService = paintingService;
        this.currentUser = currentUser;
    }

    @GetMapping("/add/{id}")
    public String add(@PathVariable Long id) {
        paintingService.addToFavorites(id);
        return "redirect:/home";
    }

    @GetMapping("/remove/{id}")
    public String delete(@PathVariable("id") Long id) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        paintingService.deleteFavourite(id);
        return "redirect:/home";
    }
}
