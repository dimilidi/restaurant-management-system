package bg.softuni.paintingcollectors.controller;

import bg.softuni.paintingcollectors.model.dto.painting.AddPaintingDTO;
import bg.softuni.paintingcollectors.model.enums.StyleNameEnum;
import bg.softuni.paintingcollectors.service.PaintingService;
import bg.softuni.paintingcollectors.service.UserService;
import bg.softuni.paintingcollectors.util.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/paintings")
public class PaintingController {
    private final UserService userService;
    private final PaintingService paintingService;
    private final CurrentUser currentUser;

    public PaintingController(UserService userService, PaintingService paintingService, CurrentUser currentUser) {
        this.userService = userService;
        this.paintingService = paintingService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("addPaintingData")
    public AddPaintingDTO initAddPaintingDTO() {
        return new AddPaintingDTO();
    }

    @ModelAttribute("styles")
    public StyleNameEnum[] styles() {
        return StyleNameEnum.values();
    }

    @GetMapping("/add")
    public String viewAddPainting() {
        return "add-painting";
    }

    @PostMapping("/add")
    public String doAddPainting(@Valid AddPaintingDTO data,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (!userService.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addPaintingData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPaintingData", bindingResult);

            return "redirect:add";
        }

        paintingService.add(data);

        return "redirect:/home";
    }


    @GetMapping("/remove/{id}")
    public String delete(@PathVariable("id") Long id) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        paintingService.delete(id);
        return "redirect:/home";
    }

}
