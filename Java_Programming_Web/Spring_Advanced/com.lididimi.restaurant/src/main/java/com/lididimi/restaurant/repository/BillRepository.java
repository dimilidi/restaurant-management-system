package com.lididimi.restaurant.repository;

import com.lididimi.restaurant.model.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long> {
    @Query("SELECT b FROM BillEntity b ORDER BY b.id DESC")
    List<BillEntity> getAllBills();

    @Query("SELECT b FROM BillEntity b WHERE b.createdBy=:username ORDER BY b.id DESC")
    List<BillEntity> getBillsByUsername(@Param("username") String username);

    @Modifying
    @Query("DELETE FROM BillEntity b WHERE b.createdDate < :cutoffDate")
    void deleteBillsOlderThan(@Param("cutoffDate") Instant cutoffDate);

    @Query("SELECT e.name AS employeeName, COUNT(b.id) AS billCount " +
        "FROM BillEntity b " +
        "JOIN UserEntity e ON b.createdBy = e.email " +
        "GROUP BY e.name " +
        "ORDER BY billCount DESC")
    List<Map<String, Object>> findTopEmployees();
}
