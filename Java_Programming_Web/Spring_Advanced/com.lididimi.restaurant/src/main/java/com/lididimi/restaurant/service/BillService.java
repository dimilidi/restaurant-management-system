package com.lididimi.restaurant.service;

import com.lididimi.restaurant.model.dto.BillDTO;
import com.lididimi.restaurant.model.entity.BillEntity;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BillService {
    String generateReport(BillDTO billDTO);

    List<BillDTO> getBills();

    byte[] getPdf(BillDTO billDTO) throws IOException;

    String delete(Long id);


}
