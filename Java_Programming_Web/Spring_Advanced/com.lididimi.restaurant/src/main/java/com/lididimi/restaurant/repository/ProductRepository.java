package com.lididimi.restaurant.repository;

import com.lididimi.restaurant.model.entity.ProductEntity;
import com.lididimi.restaurant.wrapper.ProductWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("SELECT new com.lididimi.restaurant.wrapper.ProductWrapper(p.id, p.name, p.description, p.price, p.status, p.category.id, p.category.name) FROM ProductEntity p")
    List<ProductWrapper> getAllProducts();

    @Modifying
    @Transactional
    @Query("UPDATE ProductEntity p set p.status=:status WHERE p.id=:id")
    Integer updateProductStatus(@Param("status") String status, @Param("id") Long id);

    @Query("SELECT new com.lididimi.restaurant.wrapper.ProductWrapper(p.id, p.name) FROM ProductEntity p WHERE p.category.id=:id and p.status='true'")
    List<ProductWrapper> getProductByCategory(@Param("id") Long id);

    @Query("SELECT new com.lididimi.restaurant.wrapper.ProductWrapper(p.id, p.name, p.description, p.price) FROM ProductEntity p WHERE p.id=:id")
    ProductWrapper getProductById(@Param("id") Long id);
}
