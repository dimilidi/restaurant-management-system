package dimi.lidi.hotelmanagement.repository;

import dimi.lidi.hotelmanagement.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT DISTINCT r.type FROM Room r")
    List<String> findDistinctRoomTypes();
}
