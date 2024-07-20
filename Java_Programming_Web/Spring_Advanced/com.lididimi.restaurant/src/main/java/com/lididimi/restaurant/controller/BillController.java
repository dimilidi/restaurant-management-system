package com.lididimi.restaurant.controller;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.model.dto.BillDTO;
import com.lididimi.restaurant.model.entity.BillEntity;
import com.lididimi.restaurant.service.BillService;
import com.lididimi.restaurant.utils.RestaurantUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.Max;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/generateReport")
    public ResponseEntity<?> generateReport(@RequestBody @Valid BillDTO billDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            return billService.generateReport(billDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getBills")
    public ResponseEntity<List<BillDTO>> getBills() {
        try {
            return billService.getBills();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/getPdf")
    public ResponseEntity<byte[]> getPdf(@RequestBody BillDTO billDTO) {
        try {
            return billService.getPdf(billDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            return billService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/top-employees")
    public ResponseEntity<List<Map<String, Object>>> getTopEmployees() {
        List<Map<String, Object>> topEmployees = billService.getTopEmployees();
        return ResponseEntity.ok(topEmployees);
    }



/*    @GetMapping("/regular-guests-with-top-products")
    public ResponseEntity<List<Map<String, Object>>> getRegularGuestsWithTopProducts() {
        LocalDate lastYear = LocalDate.now().minusYears(1);
        List<Map<String, Object>> result = billService.getRegularGuestsWithTopProducts(lastYear);
        return ResponseEntity.ok(result);
    }*/

/*    @GetMapping("/top-products")
    public List<Map<String, Object>> getTopProductsByEmail(@RequestParam String email) {
        return billService.findTopProductsByEmail(email);
    }
*/

    @GetMapping("/regular-guests")
    public List<Map<String, Object>> getRegularGuestsWithFavoriteProducts() {
        return billService.findRegularGuestsWithFavoriteProducts();
    }

}


