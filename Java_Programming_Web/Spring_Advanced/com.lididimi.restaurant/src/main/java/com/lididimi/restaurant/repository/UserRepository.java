package com.lididimi.restaurant.repository;

import com.lididimi.restaurant.model.entity.User;
import com.lididimi.restaurant.wrapper.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT new com.lididimi.restaurant.wrapper.UserWrapper(u.id, u.name, u.email, u.contactNumber, u.status) FROM User u WHERE u.role = 'user'")
    List<UserWrapper> getAllUsers();

    @Query("SELECT u.email FROM User u WHERE u.role = 'admin'")
    List<String> getAllAdmins();

    @Query("UPDATE User u SET u.status=:status WHERE u.id=:id")
    @Transactional
    @Modifying
    Integer updateStatus(@Param("status") String status, @Param("id") Long id);

}
