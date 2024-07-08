package com.lididimi.restaurant.model.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

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

    private String uuid;

    private String name;

    private String email;

    private String contactNumber;

    private String paymentMethod;

    private BigDecimal total;

    @Column(columnDefinition = "json")
    private String productDetails;

    private String createdBy;
}
