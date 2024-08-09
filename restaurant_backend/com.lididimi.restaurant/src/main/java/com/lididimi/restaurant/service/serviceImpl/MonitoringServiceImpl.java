package com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.service.MonitoringService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import org.springframework.stereotype.Service;

@Service
public class MonitoringServiceImpl implements MonitoringService {

    private final Counter productSearchCounter;

    public MonitoringServiceImpl(MeterRegistry meterRegistry) {
        productSearchCounter = Counter
            .builder("product.searches")
            .description("How many times products were searched")
            .register(meterRegistry);
    }
    @Override
    public void increaseProductSearches() {
        productSearchCounter.increment();
    }
}
