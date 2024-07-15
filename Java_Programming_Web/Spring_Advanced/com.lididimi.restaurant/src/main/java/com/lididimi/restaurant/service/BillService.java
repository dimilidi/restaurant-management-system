package com.lididimi.restaurant.service;

import com.lididimi.restaurant.model.entity.BillEntity;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BillService {
    ResponseEntity<String> generateReport(Map<String, Object> requestMap);

    ResponseEntity<List<BillEntity>> getBills();

    ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap) throws IOException;

    ResponseEntity<String> delete(Long id);

    void cleanupOldBillsTask();

    List<Map<String, Object>> findBestSellers();

    List<Map<String, Object>> getTopEmployees();
}
