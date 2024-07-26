package com.lididimi.restaurant.controller;

import com.lididimi.restaurant.model.response.SuccessResponse;
import com.lididimi.restaurant.service.DashboardService;
import com.lididimi.restaurant.service.serviceImpl.DashboardServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/details")
    public ResponseEntity<?> getCount() {
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", dashboardService.getCount());
        return ResponseEntity.ok(response);
    }
}
