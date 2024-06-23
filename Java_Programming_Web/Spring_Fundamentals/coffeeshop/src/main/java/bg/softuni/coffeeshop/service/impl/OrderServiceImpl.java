package bg.softuni.coffeeshop.service.impl;

import bg.softuni.coffeeshop.model.dto.OrderAddDTO;
import bg.softuni.coffeeshop.model.dto.OrderDTO;
import bg.softuni.coffeeshop.model.entity.Order;
import bg.softuni.coffeeshop.repository.OrderRepository;
import bg.softuni.coffeeshop.service.CategoryService;
import bg.softuni.coffeeshop.service.OrderService;
import bg.softuni.coffeeshop.service.UserService;
import bg.softuni.coffeeshop.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final  CurrentUser currentUser;
    private final UserService userService;
    private final CategoryService categoryService;


    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, CurrentUser currentUser, UserService userService, CategoryService categoryService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void addOrder(OrderAddDTO orderAddDTO) {
        Order order = modelMapper.map(orderAddDTO, Order.class);

        order.setEmployee(userService.findById(currentUser.getId()));
        order.setCategory(categoryService.findByCategoryNameEnum(orderAddDTO.getCategory()));

        orderRepository.save(order);
    }

    @Override
    public List<OrderDTO> findAllOrderOrdersByPriceDesc() {
       return  orderRepository.findAllByOrderByPriceDesc()
                .stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void readyOrder(Long id) {
        orderRepository.deleteById(id);
    }


}

