/*

package com.lididimi.restaurant.service.serviceImpl;

import com.github.javafaker.Faker;
import com.lididimi.restaurant.model.entity.BillEntity;
import com.lididimi.restaurant.model.enums.PaymentMethodNameEnum;
import com.lididimi.restaurant.model.dto.bill.BillDTO;
import com.lididimi.restaurant.repository.BillRepository;
import com.lididimi.restaurant.service.BillService;
import com.lididimi.restaurant.service.SeedDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.logging.Logger;


@Service
public class SeedDataServiceImpl implements SeedDataService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillService billService;

    private Faker faker = new Faker();
    private static final Logger logger = Logger.getLogger(SeedDataServiceImpl.class.getName());

    @Override
    @PostConstruct
    public void generateSeedData() {
        String name = "Anna Andonova";
        String email = "anna@example.com";

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.minusDays(365);

        for (LocalDate date = startDate; !date.isBefore(endDate); date = date.minusDays(1)) {
            String uuid = "BILL-" + faker.number().digits(15);
            String contactNumber = "1231231234";
            PaymentMethodNameEnum paymentMethod = PaymentMethodNameEnum.DEBIT_CARD;
            BigDecimal total = BigDecimal.valueOf(2.29).multiply(BigDecimal.valueOf(20));
            String productDetails = "[{\"id\": 1, \"name\": \"Cheese Cake\", \"price\": 10.00, \"total\": 20.00, \"category\": \"Cake\", \"quantity\": \"2\"}]";
            String createdBy = "john@example.com";

            // Set createdDate to the start of the current day in the loop
            Instant createdDate = date.atStartOfDay().toInstant(ZoneOffset.UTC);
            logger.info("Creating bill for date: " + date + " with createdDate: " + createdDate);

            BillEntity bill = new BillEntity();
            bill.setUuid(uuid);
            bill.setName(name);
            bill.setEmail(email);
            bill.setContactNumber(contactNumber);
            bill.setPaymentMethod(paymentMethod);
            bill.setTotal(total);
            bill.setProductDetails(productDetails);
            bill.setCreatedBy(createdBy);
            bill.setCreatedDate(createdDate);

            billRepository.save(bill);

            // Generate PDF for the seeded bill
            BillDTO billDTO = new BillDTO();
            billDTO.setUuid(uuid);
            billDTO.setName(name);
            billDTO.setEmail(email);
            billDTO.setContactNumber(contactNumber);
            billDTO.setPaymentMethod(paymentMethod);
            billDTO.setTotal(total);
            billDTO.setProductDetails(productDetails);
            billDTO.setCreatedBy(createdBy);
            billDTO.setCreatedDate(createdDate);
            billDTO.setGenerate(true);

            billService.generateReport(billDTO);
        }
    }
}







*/
