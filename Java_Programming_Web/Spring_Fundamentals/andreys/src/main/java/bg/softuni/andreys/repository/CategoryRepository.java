package bg.softuni.andreys.repository;

import bg.softuni.andreys.model.entity.CategoryEntity;
import bg.softuni.andreys.model.enums.CategotyNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByName(CategotyNameEnum name);
}
