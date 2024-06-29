package com.lididimi.restaurant.model.entity;

import javax.persistence.*;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private Date expiryDate;

    public boolean isExpired() {
        return new Date().after(this.expiryDate);
    }
}
