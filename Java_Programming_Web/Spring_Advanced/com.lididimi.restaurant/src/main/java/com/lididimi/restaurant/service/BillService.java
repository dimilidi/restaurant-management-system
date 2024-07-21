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

    ResponseEntity<List<BillDTO>> getBills();

    ResponseEntity<byte[]> getPdf(BillDTO billDTO) throws IOException;

    ResponseEntity<String> delete(Long id);

    List<Map<String, Object>> findBestSellers();

    List<Map<String, Object>> getTopEmployees();

    List<Map<String, Object>> findRegularGuestsWithFavoriteProducts();


}
