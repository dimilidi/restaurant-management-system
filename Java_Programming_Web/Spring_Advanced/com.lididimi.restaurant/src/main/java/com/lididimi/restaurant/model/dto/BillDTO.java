package com.lididimi.restaurant.model.dto;

import com.lididimi.restaurant.model.enums.PaymentMethodNameEnum;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;


public class BillDTO {

    @NotNull
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Pattern(regexp = "^0[0-9]{9}$", message = "Phone number must start with 0 and be exactly 10 digits")
    private String contactNumber;

    @NotNull
    @NotBlank(message = "Payment method is mandatory")
    private PaymentMethodNameEnum paymentMethod;

    @NotNull(message = "Total amount is mandatory")
    @Positive(message = "Total amount must be positive")
    private BigDecimal total;

    @NotBlank(message = "Product details are mandatory")
    private String productDetails;

}
