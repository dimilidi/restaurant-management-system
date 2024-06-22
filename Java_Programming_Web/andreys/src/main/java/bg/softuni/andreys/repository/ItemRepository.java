package bg.softuni.andreys.repository;

import bg.softuni.andreys.model.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
