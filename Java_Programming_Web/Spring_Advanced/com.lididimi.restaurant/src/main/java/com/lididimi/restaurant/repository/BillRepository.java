package com.lididimi.restaurant.repository;

import com.lididimi.restaurant.model.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long> {
    @Query("SELECT b FROM BillEntity b ORDER BY b.id DESC")
    List<BillEntity> getAllBills();

    List<BillEntity> findByCreatedBy(String createdBy);

    List<BillEntity> findByEmail(String email);

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

    @Query("SELECT b.email AS email, b.name AS name, COUNT(b.id) AS billCount " +
            "FROM BillEntity b " +
            "WHERE b.createdDate >= :lastYear " +
            "GROUP BY b.email, b.name " +
            "HAVING COUNT(b.id) >= 365")
    List<Map<String, Object>> findRegularGuestsWithAtLeast365Bills(@Param("lastYear") Instant lastYear);


 /*   @Query(value = "SELECT JSON_UNQUOTE(JSON_EXTRACT(p.product, '$.name')) AS productName, COUNT(*) AS orderCount " +
            "FROM bills b, JSON_TABLE(b.product_details, '$[*]' COLUMNS(" +
            "product JSON PATH '$')) AS p " +
            "WHERE b.createdBy = :email AND JSON_UNQUOTE(JSON_EXTRACT(p.product, '$.product_type')) = 'compounded' " +
            "GROUP BY productName " +
            "ORDER BY orderCount DESC " +
            "LIMIT 3", nativeQuery = true)
    List<Map<String, Object>> findTopProductsByEmail(@Param("email") String email);
*/








}
