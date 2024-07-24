package com.lididimi.restaurant.restaurant_categories.repository;

import com.lididimi.restaurant.restaurant_categories.model.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByName(String name);


  //  @Query("SELECT c FROM CategoryEntity c WHERE c.id IN (SELECT p.category.id FROM ProductEntity p WHERE p.status = 'ACTIVE')")
   //Optional<List<CategoryEntity>> getAllCategories();

    Optional<CategoryEntity> findByName(String name);
}
