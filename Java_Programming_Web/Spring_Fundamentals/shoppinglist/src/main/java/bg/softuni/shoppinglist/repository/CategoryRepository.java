package bg.softuni.shoppinglist.repository;

import bg.softuni.shoppinglist.model.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import bg.softuni.shoppinglist.model.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    Optional<CategoryEntity> findByName(CategoryNameEnum categoryNameEnum);
}
