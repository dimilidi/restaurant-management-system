package bg.softuni.andreys.web;

import bg.softuni.andreys.model.dto.ItemAddDTO;
import bg.softuni.andreys.model.dto.ItemViewDTO;
import bg.softuni.andreys.model.dto.UserRegisterDTO;
import bg.softuni.andreys.model.entity.CategoryEntity;
import bg.softuni.andreys.model.enums.CategotyNameEnum;
import bg.softuni.andreys.model.enums.GenderEnum;
import bg.softuni.andreys.repository.CategoryRepository;
import bg.softuni.andreys.service.ItemService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemsController {

    private final CategoryRepository categoryRepository;
    private final ItemService itemService;

    public ItemsController(CategoryRepository categoryRepository, ItemService itemService) {
        this.categoryRepository = categoryRepository;
        this.itemService = itemService;
    }

    @ModelAttribute("categories")
    public List<CategotyNameEnum> categories() {
        return Arrays.stream(CategotyNameEnum.values()).toList();
      // return categoryRepository.findAll().stream().map(CategoryEntity::getName).toList();
    }

    @ModelAttribute("genders")
    public List<GenderEnum> genders() {
       return Arrays.stream(GenderEnum.values()).toList();
    }

    @GetMapping("/add")
    public String add(Model model, HttpSession httpSession) {
        if(httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }

        if (!model.containsAttribute("itemsData")) {
            model.addAttribute("itemsData", new ItemAddDTO());
        }
        return "add-item";
    }

    @PostMapping("/add")
    public String doAdd(@Valid ItemAddDTO data,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                        HttpSession httpSession) {
        if(httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("itemsData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.itemsData", bindingResult);

            return "redirect:/items/add";
        }

       itemService.add(data);

        return "redirect:/";
    }

    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id") Long id, ModelAndView modelAndView, HttpSession httpSession) {
        if(httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }

        ItemViewDTO item = itemService.findById(id);
        if (item == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }

        modelAndView.addObject("item", item);
        modelAndView.setViewName("details-item");
        return modelAndView;
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, HttpSession httpSession) {
        if(httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }
        itemService.delete(id);
        return "redirect:/";
    }
}
