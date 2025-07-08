package projetb2.overlook_hotel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import projetb2.overlook_hotel.model.Booking;
import projetb2.overlook_hotel.repository.BookingRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepo;

    public BookingService(BookingRepository bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }
}
