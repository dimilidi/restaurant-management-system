package bg.softuni.paintingcollectors.repository;

import bg.softuni.paintingcollectors.model.entity.StyleEntity;
import bg.softuni.paintingcollectors.model.enums.StyleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StyleRepository extends JpaRepository<StyleEntity, Long> {
    Optional<StyleEntity> findByName(StyleNameEnum style);
}
