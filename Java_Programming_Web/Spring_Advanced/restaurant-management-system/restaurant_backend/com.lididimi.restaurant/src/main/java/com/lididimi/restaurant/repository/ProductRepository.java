package com.lididimi.restaurant.repository;


import com.lididimi.restaurant.model.entity.ProductEntity;
import com.lididimi.restaurant.model.enums.StatusNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    boolean existsByName(String name);

    @Query("SELECT p FROM ProductEntity p")
    Optional<List<ProductEntity>> getAllProducts();

    @Query("SELECT p FROM ProductEntity p WHERE p.status = 'ACTIVE'")
    List<ProductEntity> findAllActiveProducts();

    @Modifying
    @Transactional
    @Query("UPDATE ProductEntity p set p.status=:status WHERE p.id=:id")
    Integer updateProductStatus(@Param("status") StatusNameEnum status, @Param("id") Long id);


    @Query("SELECT p FROM ProductEntity p WHERE p.categoryId=:id and p.status='ACTIVE'")
    Optional<List<ProductEntity>> getProductByCategory(@Param("id") Long id);

    @Query("SELECT p FROM ProductEntity p WHERE p.id=:id")
    Optional<ProductEntity> getProductById(@Param("id") Long id);

    @Query("SELECT p FROM ProductEntity p WHERE p.categoryId = :categoryId")
    List<ProductEntity> findByCategoryId(@Param("categoryId") Long categoryId);

}
