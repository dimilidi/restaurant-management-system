package com.lididimi.restaurant.repository;

import com.lididimi.restaurant.model.dto.ProductDTO;
import com.lididimi.restaurant.model.entity.ProductEntity;
import com.lididimi.restaurant.model.enums.StatusNameEnum;
import com.lididimi.restaurant.wrapper.ProductWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    boolean existsByName(String name);

    @Query("SELECT p FROM ProductEntity p")
    List<ProductEntity> getAllProducts();

    @Modifying
    @Transactional
    @Query("UPDATE ProductEntity p set p.status=:status WHERE p.id=:id")
    Integer updateProductStatus(@Param("status") StatusNameEnum status, @Param("id") Long id);

    @Transactional
    @Query("SELECT p FROM ProductEntity p WHERE p.category.id=:id and p.status='ACTIVE'")
    List<ProductDTO> getProductByCategory(@Param("id") Long id);

    @Query("SELECT p FROM ProductEntity p WHERE p.id=:id")
    ProductDTO getProductById(@Param("id") Long id);
}
