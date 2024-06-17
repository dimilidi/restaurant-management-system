package bg.softuni.coffeeshop.web;

import bg.softuni.coffeeshop.model.dto.OrderAddDTO;
import bg.softuni.coffeeshop.model.entity.CategoryNameEnum;
import bg.softuni.coffeeshop.service.OrderService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("orderAddDTO")
    public OrderAddDTO orderAddDTO() {
        return new OrderAddDTO();
    }

    @ModelAttribute("categories")
    public CategoryNameEnum[] categories() {
        return CategoryNameEnum.values();
    }

    @GetMapping("/add")
    public String add() {
        return "order-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid OrderAddDTO orderAddDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderAddDTO", orderAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderAddDTO", bindingResult);
            return "redirect:/orders/add";
        }

        orderService.addOrder(orderAddDTO);


        return "redirect:/home";
    }

    @GetMapping("/ready/{id}")
    public String ready(@PathVariable Long id) {
        orderService.readyOrder(id);

        return "redirect:/home";
    }
}
