package bg.softuni.paintingcollectors.controller;

import bg.softuni.paintingcollectors.service.PaintingService;
import bg.softuni.paintingcollectors.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vote")
public class VotingController {
    private final PaintingService paintingService;
    private final CurrentUser currentUser;

    public VotingController(PaintingService paintingService, CurrentUser currentUser) {
        this.paintingService = paintingService;
        this.currentUser = currentUser;
    }

    @GetMapping("/{id}")
    public String vote(@PathVariable("id") Long id) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        paintingService.vote(id);
        return "redirect:/home";
    }
}

