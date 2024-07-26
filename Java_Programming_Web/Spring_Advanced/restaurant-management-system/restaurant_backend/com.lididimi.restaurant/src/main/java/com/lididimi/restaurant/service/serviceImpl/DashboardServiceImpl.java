package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.repository.BillRepository;
import com.lididimi.restaurant.repository.ProductRepository;
import com.lididimi.restaurant.service.CategoryService;
import com.lididimi.restaurant.service.DashboardService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {
    private final ProductRepository productRepository;
    private final BillRepository billRepository;
    private final CategoryService categoryService;

    public DashboardServiceImpl(ProductRepository productRepository, BillRepository billRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.billRepository = billRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Map<String, Object> getCount() {
            Map<String, Object> countMap = new HashMap<>();
            countMap.put("category",categoryService.getCategoriesCount());
            countMap.put("product", productRepository.count());
            countMap.put("bill", billRepository.count());
            return countMap;
    }
}
