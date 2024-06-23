package bg.softuni.Pathfinder.data;

import bg.softuni.Pathfinder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    User findByUsername(String username);
}
