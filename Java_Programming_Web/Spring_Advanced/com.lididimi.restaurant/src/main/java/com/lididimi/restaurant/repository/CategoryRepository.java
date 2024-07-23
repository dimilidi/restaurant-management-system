/*
package com.lididimi.restaurant.repository;

import com.lididimi.restaurant.model.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByName(String name);

    @Query("SELECT c FROM CategoryEntity c WHERE c.id IN (SELECT p.category.id FROM ProductEntity p WHERE p.status = 'ACTIVE')")
   Optional<List<CategoryEntity>> getAllCategories();

}
*/
