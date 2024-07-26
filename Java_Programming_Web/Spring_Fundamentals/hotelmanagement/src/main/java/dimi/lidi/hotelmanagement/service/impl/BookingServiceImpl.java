package dimi.lidi.hotelmanagement.service.impl;

import dimi.lidi.hotelmanagement.model.BookedRoom;
import dimi.lidi.hotelmanagement.repository.BookedRoomRepository;
import dimi.lidi.hotelmanagement.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookingServiceImpl implements BookingService {
    private final BookedRoomRepository bookedRoomRepository;

    public BookingServiceImpl(BookedRoomRepository bookedRoomRepository) {
        this.bookedRoomRepository = bookedRoomRepository;
    }

    @Override
    public List<BookedRoom> getAllBookings(Long roomId) {
        return bookedRoomRepository.findAll();
    }

    @Override
    public List<BookedRoom> getAllBookingsByRoomId(Long roomId) {
        return bookedRoomRepository.findByRoomId(roomId);
    }
}
