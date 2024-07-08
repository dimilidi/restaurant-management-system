package com.lididimi.restaurant.repository;

import com.lididimi.restaurant.model.entity.UserEntity;
import com.lididimi.restaurant.wrapper.UserWrapper;
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

    @Query("SELECT new com.lididimi.restaurant.wrapper.UserWrapper(u.id, u.name, u.email, u.contactNumber, u.status) " +
            "FROM UserEntity u JOIN u.roles r WHERE r.name = 'user'")
    List<UserWrapper> getAllUsers();


    @Query("SELECT u.email FROM UserEntity u JOIN u.roles r WHERE r.name = 'admin'")
    List<String> getAllAdmins();


    @Query("UPDATE UserEntity u SET u.status=:status WHERE u.id=:id")
    @Transactional
    @Modifying
    Integer updateStatus(@Param("status") String status, @Param("id") Long id);


}
