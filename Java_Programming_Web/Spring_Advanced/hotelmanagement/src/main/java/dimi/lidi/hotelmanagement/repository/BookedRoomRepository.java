package dimi.lidi.hotelmanagement.repository;

import dimi.lidi.hotelmanagement.model.BookedRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookedRoomRepository extends JpaRepository<BookedRoom, Long> {
}
