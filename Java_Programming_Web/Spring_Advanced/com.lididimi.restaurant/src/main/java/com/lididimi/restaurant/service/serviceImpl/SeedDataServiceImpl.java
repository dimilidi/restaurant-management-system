package com.lididimi.restaurant.service.serviceImpl;

import com.github.javafaker.Faker;
import com.lididimi.restaurant.model.entity.BillEntity;
import com.lididimi.restaurant.repository.BillRepository;
import com.lididimi.restaurant.service.SeedDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Service
public class SeedDataServiceImpl implements SeedDataService {

 @Autowired
    private BillRepository billRepository;

    private Faker faker = new Faker();

       @Override
    @PostConstruct
    public void generateSeedData() {
     String name = "Sara Smith";
        String email = "sara.smith@example.com";

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.minusDays(404); // 365 days back

        for (LocalDate date = startDate; !date.isBefore(endDate); date = date.minusDays(1)) {
            String uuid = "BILL-" + faker.number().digits(15);
            String contactNumber = "1231231234";
            String paymentMethod = "DEBIT_CARD";
            BigDecimal total = BigDecimal.valueOf(2.29).multiply(BigDecimal.valueOf(20));
            String productDetails = "[{\"id\": 1, \"name\": \"Dark Cappuccino\", \"price\": 3.00, \"total\": 44.80, \"category\": \"Cappuccino\", \"quantity\": \"22\"}]";
            String createdBy = "user@mail.de";

            BillEntity bill = new BillEntity();
            bill.setUuid(uuid);
            bill.setName(name);
            bill.setEmail(email);
            bill.setContactNumber(contactNumber);
            bill.setPaymentMethod(paymentMethod);
            bill.setTotal(total);
            bill.setProductDetails(productDetails);
            bill.setCreatedBy(createdBy);
            // Set createdDate using a different hour/minute/second for variety
            Instant createdDate = date.atStartOfDay(ZoneOffset.UTC)
                    .plusSeconds(faker.number().numberBetween(0, 86400)) // Random seconds within the day
                    .toInstant();
            bill.setCreatedDate(createdDate);

            billRepository.save(bill);
        }
    }
}
