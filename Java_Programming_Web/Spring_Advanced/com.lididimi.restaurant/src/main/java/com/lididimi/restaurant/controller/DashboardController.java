package com.lididimi.restaurant.controller;

import com.lididimi.restaurant.service.DashboardService;
import com.lididimi.restaurant.service.serviceImpl.DashboardServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardServiceImpl dashboardServiceImpl;
    private final DashboardService dashboardService;

    public DashboardController(DashboardServiceImpl dashboardServiceImpl, DashboardService dashboardService) {
        this.dashboardServiceImpl = dashboardServiceImpl;
        this.dashboardService = dashboardService;
    }

    @GetMapping("/details")
    public ResponseEntity<Map<String, Object>> getCount() {
        return dashboardService.getCount();
    }

}
