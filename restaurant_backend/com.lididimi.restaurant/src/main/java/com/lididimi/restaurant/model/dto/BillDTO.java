package com.lididimi.restaurant.model.dto;

import com.lididimi.restaurant.model.enums.PaymentMethodNameEnum;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {
    private Long id;

    private String uuid;

    @NotNull(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Pattern(regexp = "^0[0-9]{9}$", message = "Phone number must start with 0 and be exactly 10 digits")
    private String contactNumber;

    @NotNull(message = "Payment method is mandatory")
    private PaymentMethodNameEnum paymentMethod;

    @NotNull(message = "Total amount is mandatory")
    @Positive(message = "Total amount must be positive")
    private BigDecimal total;

    @NotNull(message = "Product details are mandatory")
    @NotBlank(message = "Product details are mandatory")
    private String productDetails;

    private boolean isGenerate;

    private String createdBy;

    private Instant createdDate;
}
