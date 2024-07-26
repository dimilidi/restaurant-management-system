package com.lididimi.restaurant.scheduler;

import com.lididimi.restaurant.repository.BillRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Component
public class BillCleanupScheduler {
    private final BillRepository billRepository;

    public BillCleanupScheduler(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Scheduled(cron = "0 0 2 * * ?") // Runs every day at 2 AM
    @Transactional
    public void cleanupOldBillsTask() {
        log.info("Starting cleanup of old bills at {}", Instant.now());
        System.out.println("Current Time Zone: " + java.util.TimeZone.getDefault().getID());
        cleanupOldBills();
    }

    @Transactional
    public void cleanupOldBills() {
        Instant cutoffDate = Instant.now().minusSeconds(365L * 24 * 60 * 60); // Keep bills for 1 year
        billRepository.deleteBillsOlderThan(cutoffDate);
    }

    // Test scheduled task
   //@Scheduled(cron = "*/10 * * * * ?") // Runs every 10 seconds
  /*     @Transactional
    public void cleanupOldBillsTask() {
        Instant cutoffDate = Instant.now().minusSeconds(60); // 1 minute ago
        billRepository.deleteBillsOlderThan(cutoffDate);
        log.info("Old bills cleaned up");
    }*/
}
