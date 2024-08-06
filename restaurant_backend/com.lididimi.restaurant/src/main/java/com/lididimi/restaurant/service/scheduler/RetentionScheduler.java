package com.lididimi.restaurant.service.scheduler;

import com.lididimi.restaurant.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Slf4j
@Component
public class RetentionScheduler {
    private final BillService billService;

    public RetentionScheduler(BillService billService) {
        this.billService = billService;
    }

     @Scheduled(cron = "0 0 2 * * ?") // Runs every day at 2 AM
    //  @Scheduled(cron = "*/10 * * * * ?") // Runs every 10 seconds
    public void cleanupOldBills() {
        log.info("Starting cleanup of old bills at {}", Instant.now());
        System.out.println("Current Time Zone: " + java.util.TimeZone.getDefault().getID());
        billService. cleanupOldBills();
       log.info("Cleanup of old bills finished at {}", Instant.now());
    }
}
