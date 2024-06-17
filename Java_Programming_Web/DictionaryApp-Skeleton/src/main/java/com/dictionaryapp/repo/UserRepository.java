package com.dictionaryapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  com.dictionaryapp.model.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsernameOrEmail(String username, String email);

    Optional<User> findByEmail(String value);

    Optional<User> findByUsername( String value);

    Optional<User> findByUsernameAndPassword(String username, String password);
}
