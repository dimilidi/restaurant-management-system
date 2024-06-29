package com.lididimi.restaurant.repository;

import com.lididimi.restaurant.model.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query("SELECT c FROM CategoryEntity c")
    List<CategoryEntity> getAllCategories();

}