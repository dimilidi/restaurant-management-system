package com.lididimi.restaurant.service;

import com.lididimi.restaurant.model.dto.bill.BillDTO;

import java.io.IOException;
import java.util.List;

public interface BillService {
    String generateReport(BillDTO billDTO);

    List<BillDTO> getBills();

    byte[] getPdf(BillDTO billDTO) throws IOException;

    String delete(Long id);

    void cleanupOldBills();
}
