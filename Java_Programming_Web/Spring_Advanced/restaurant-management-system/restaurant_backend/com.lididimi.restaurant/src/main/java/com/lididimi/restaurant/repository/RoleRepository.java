package com.lididimi.restaurant.repository;

import com.lididimi.restaurant.model.entity.RoleEntity;
import com.lididimi.restaurant.model.enums.UserRoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(UserRoleNameEnum user);
}
