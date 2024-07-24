package com.lididimi.restaurant.controller;

import com.lididimi.restaurant.model.dto.BestSellerDTO;
import com.lididimi.restaurant.model.dto.GuestDTO;
import com.lididimi.restaurant.model.dto.TopEmployeeDTO;
import com.lididimi.restaurant.model.response.SuccessResponse;
import com.lididimi.restaurant.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/best-sellers")
    public ResponseEntity<?> getBestSellers() {
        List<BestSellerDTO> bestSellers = reportService.findBestSellers();
        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK.value(), "", bestSellers));
    }

    @GetMapping("/top-employees")
    public ResponseEntity<?> getTopEmployees() {
        List<TopEmployeeDTO> topEmployees = reportService.getTopEmployees();
        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK.value(), "", topEmployees));
    }

    @GetMapping("/regular-guests")
    public ResponseEntity<?> getRegularGuestsWithFavoriteProducts() {
        List<GuestDTO>result = reportService.findRegularGuestsWithFavoriteProducts();
        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK.value(), "", result));
    }
}
