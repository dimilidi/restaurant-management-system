package bg.softuni.shoppinglist.web;

import bg.softuni.shoppinglist.model.dto.ProductAddDTO;
import bg.softuni.shoppinglist.model.enums.CategoryNameEnum;
import bg.softuni.shoppinglist.service.ProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute("categories")
    public CategoryNameEnum[] categories() {
        return CategoryNameEnum.values();
    }


    @GetMapping("/add")
    public String add(Model model, HttpSession httpSession) {
        if(httpSession.getAttribute("user") == null) {
            return "redirect:/users/login";
        }

        if(!model.containsAttribute("productData")) {
            model.addAttribute("productData", new ProductAddDTO());
        }
        return "product-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid ProductAddDTO productData,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {



        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productData", productData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productData", bindingResult);

            return "redirect:add";
        }


        productService.add(productData);

        return "redirect:/";
    }

    @GetMapping("/buy/{id}")
    public String buyById(@PathVariable UUID id) {
        productService.buyById(id);
        return "redirect:/";
    }

    @GetMapping("/buy/all")
    public String buyAll() {
        productService.buyAll();

        return "redirect:/";
    }
}
