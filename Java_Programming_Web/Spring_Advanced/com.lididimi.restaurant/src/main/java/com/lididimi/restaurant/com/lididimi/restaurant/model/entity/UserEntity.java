package com.lididimi.restaurant.com.lididimi.restaurant.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Entity
@Data
@DynamicUpdate
@DynamicInsert
@Table(name = "users")
public class UserEntity implements Serializable {
    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contactNumber;
    private String email;
    private String password;
    private String status;
    private String role;
}
