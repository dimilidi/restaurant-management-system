package bg.softuni.paintingcollectors.repository;

import bg.softuni.paintingcollectors.model.entity.PaintingEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaintingRepository extends JpaRepository<PaintingEntity, Long> {

    @Query(value = "SELECT p FROM PaintingEntity p ORDER BY p.votes DESC, p.name ASC")
    List<PaintingEntity> findTopTwoByOrderByVotesDescNameAsc(Pageable pageable);
}
