package bg.softuni.coffeeshop.service;

import bg.softuni.coffeeshop.model.dto.OrderAddDTO;
import bg.softuni.coffeeshop.model.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    void addOrder(OrderAddDTO orderAddDTO);

    List<OrderDTO> findAllOrderOrdersByPriceDesc();


    void readyOrder(Long id);

}
