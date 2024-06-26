package com.lididimi.restaurant.com.lididimi.restaurant.repository;

import com.lididimi.restaurant.com.lididimi.restaurant.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
