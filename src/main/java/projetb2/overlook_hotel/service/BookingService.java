package projetb2.overlook_hotel.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projetb2.overlook_hotel.model.Booking;
import projetb2.overlook_hotel.model.BookingStatus;
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

    @Transactional
    public boolean acceptBooking(Long bookingId) {
        return bookingRepo.findById(bookingId).map(booking -> {
            if (booking.getBookingStatus() != BookingStatus.PENDING)
                return false;
            booking.setBookingStatus(BookingStatus.ACCEPTED);
            bookingRepo.save(booking);
            return true;
        }).orElse(false);
    }

    @Transactional
    public boolean declineBooking(Long bookingId) {
        return bookingRepo.findById(bookingId).map(booking -> {
            if (booking.getBookingStatus() != BookingStatus.PENDING)
                return false;
            booking.setBookingStatus(BookingStatus.DECLINED);
            bookingRepo.save(booking);
            return true;
        }).orElse(false);
    }
}
