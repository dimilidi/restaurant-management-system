package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.repository.BillRepository;
import com.lididimi.restaurant.repository.CategoryRepository;
import com.lididimi.restaurant.repository.ProductRepository;
import com.lididimi.restaurant.service.DashboardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final BillRepository billRepository;

    public DashboardServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository, BillRepository billRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.billRepository = billRepository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        Map<String, Object> map = new HashMap<>();
        map.put("category", categoryRepository.count());
        map.put("product", productRepository.count());
        map.put("bill", billRepository.count());

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
