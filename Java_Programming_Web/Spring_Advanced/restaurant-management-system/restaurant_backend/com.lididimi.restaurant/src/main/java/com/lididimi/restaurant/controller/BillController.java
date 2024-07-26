package com.lididimi.restaurant.controller;

import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.model.response.ErrorResponse;
import com.lididimi.restaurant.model.dto.BillDTO;
import com.lididimi.restaurant.model.response.SuccessResponse;
import com.lididimi.restaurant.service.BillService;
import com.lididimi.restaurant.utils.RestaurantUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        String bill = billService.generateReport(billDTO);

        return RestaurantUtils.getResponseEntity(bill, HttpStatus.OK);
    }

    @PostMapping("/getPdf")
    public ResponseEntity<?> getPdf(@RequestBody BillDTO billDTO) {
        try {
            byte[] pdf = billService.getPdf(billDTO);
            return new ResponseEntity<>(pdf, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), RestaurantConstants.PDF_GENERATION_FAILURE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        String responseMessage = billService.delete(id);
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), responseMessage, null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getBills")
    public ResponseEntity<?> getBills() {
        List<BillDTO> bills = billService.getBills();
        SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(), "", bills);
        return ResponseEntity.ok(response);
    }

}


