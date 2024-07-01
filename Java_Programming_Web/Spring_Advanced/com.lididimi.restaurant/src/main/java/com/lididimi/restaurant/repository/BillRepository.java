package com.lididimi.restaurant.repository;

import com.lididimi.restaurant.model.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long> {
    @Query("SELECT b FROM BillEntity b ORDER BY b.id DESC")
    List<BillEntity> getAllBills();

    @Query("SELECT b FROM BillEntity b WHERE b.createdBy=:username ORDER BY b.id DESC")
    List<BillEntity> getBillsByUsername(@Param("username") String username);
}
