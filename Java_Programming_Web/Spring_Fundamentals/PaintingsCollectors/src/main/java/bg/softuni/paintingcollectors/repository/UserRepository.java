package bg.softuni.paintingcollectors.repository;


import bg.softuni.paintingcollectors.model.entity.PaintingEntity;
import bg.softuni.paintingcollectors.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsernameOrEmail(String username, String email);

    Optional<UserEntity> findByEmail(String value);

    Optional<UserEntity> findByUsername( String value);

    @Query("SELECT u.favoritePaintings FROM UserEntity u WHERE u.id = :userId")
    List<PaintingEntity> findFavoritePaintingsByUserId(@Param("userId") Long userId);

}
