package dimi.lidi.hotelmanagement.service;

import dimi.lidi.hotelmanagement.model.BookedRoom;

import java.util.List;

public interface BookingService {
    List<BookedRoom> getAllBookings(Long roomId);
    List<BookedRoom> getAllBookingsByRoomId(Long roomId);
}
