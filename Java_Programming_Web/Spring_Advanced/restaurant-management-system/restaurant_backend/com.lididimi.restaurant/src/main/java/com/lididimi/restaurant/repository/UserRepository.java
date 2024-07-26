package com.lididimi.restaurant.repository;

import com.lididimi.restaurant.model.entity.UserEntity;
import com.lididimi.restaurant.model.enums.StatusNameEnum;
import com.lididimi.restaurant.model.enums.UserRoleNameEnum;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT u FROM UserEntity u JOIN u.roles r WHERE r.name = :roleName")
    List<UserEntity> getAllUsers(@Param("roleName") UserRoleNameEnum roleName);


    @Query("SELECT u.email FROM UserEntity u JOIN u.roles r WHERE r.name = :roleName")
    List<String> getAllAdmins(@Param("roleName") UserRoleNameEnum roleName);


    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.status=:status WHERE u.id=:id")
    Integer updateStatus(@Param("status") StatusNameEnum status, @Param("id") Long id);


    boolean existsByEmail(String email);
}
