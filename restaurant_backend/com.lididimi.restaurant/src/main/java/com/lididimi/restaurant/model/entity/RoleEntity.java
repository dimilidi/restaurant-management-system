package com.lididimi.restaurant.model.entity;

import com.lididimi.restaurant.model.enums.UserRoleNameEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Data
@DynamicUpdate
@DynamicInsert
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private UserRoleNameEnum name;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users;
}
