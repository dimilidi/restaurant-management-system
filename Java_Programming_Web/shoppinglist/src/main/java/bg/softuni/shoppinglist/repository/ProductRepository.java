package bg.softuni.shoppinglist.repository;

import bg.softuni.shoppinglist.model.entity.ProductEntity;
import bg.softuni.shoppinglist.model.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    @Query("SELECT SUM(p.price) FROM ProductEntity p")
    BigDecimal findTotalProductSum();

    List<ProductEntity> findAllByCategory_Name(CategoryNameEnum categoryNameEnum);
}
