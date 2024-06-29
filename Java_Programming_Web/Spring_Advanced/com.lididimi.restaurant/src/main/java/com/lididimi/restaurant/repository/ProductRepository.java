package com.lididimi.restaurant.repository;

import com.lididimi.restaurant.model.entity.ProductEntity;
import com.lididimi.restaurant.wrapper.ProductWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("SELECT new com.lididimi.restaurant.wrapper.ProductWrapper(p.id, p.name, p.description, p.price, p.status, p.category.id, p.category.name) FROM ProductEntity p")
    List<ProductWrapper> getAllProducts();

}
