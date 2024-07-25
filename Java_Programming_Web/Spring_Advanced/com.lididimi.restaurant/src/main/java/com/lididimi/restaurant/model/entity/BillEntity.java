package com.lididimi.restaurant.model.entity;

import com.lididimi.restaurant.model.enums.PaymentMethodNameEnum;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
@DynamicUpdate
@DynamicInsert
@Table(name = "bills")
public class BillEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethodNameEnum paymentMethod;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false,columnDefinition = "CLOB")
    private String productDetails;

    @Column(nullable = false)
    private String createdBy;

    @CreationTimestamp
    @Column(nullable = false)
    private Instant createdDate;
}
