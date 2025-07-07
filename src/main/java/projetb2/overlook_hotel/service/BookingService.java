package projetb2.overlook_hotel.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projetb2.overlook_hotel.model.Booking;
import projetb2.overlook_hotel.model.BookingStatus;
import projetb2.overlook_hotel.repository.BookingRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepo;

    /**
     * Constructor for BookingService.
     *
     * @param bookingRepo the BookingRepository to be used by this service
     */
    public BookingService(BookingRepository bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    /**
     * Saves a booking to the repository.
     *
     * @param booking the Booking object to be saved
     * @return the saved Booking object
     */
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    /*
     * Accepts a booking by its ID.
     * If the booking is successfully accepted, it returns true.
     */
    @Transactional
    public boolean acceptBooking(Integer bookingId) {
        return bookingRepo.findById(bookingId).map(booking -> {
            if (booking.getBookingStatus() != BookingStatus.PENDING)
                return false;
            booking.setBookingStatus(BookingStatus.ACCEPTED);
            bookingRepo.save(booking);
            return true;
        }).orElse(false);
    }

    /*
     * Declines a booking by its ID.
     * If the booking is successfully declined, it returns true.
     */
    @Transactional
    public boolean declineBooking(Integer bookingId) {
        return bookingRepo.findById(bookingId).map(booking -> {
            if (booking.getBookingStatus() != BookingStatus.PENDING)
                return false;
            booking.setBookingStatus(BookingStatus.DECLINED);
            bookingRepo.save(booking);
            return true;
        }).orElse(false);
    }

    /*
     * Updates a booking with the provided details.
     * If the booking is successfully updated, it returns true.
     */
    @Transactional
    public boolean updateBooking(Integer bookingId, LocalDate arrival, LocalDate departure, BookingStatus status) {
        return bookingRepo.findById(bookingId).map(booking -> {
            booking.setArrivingDate(arrival);
            booking.setDepartureDate(departure);
            booking.setBookingStatus(status);
            bookingRepo.save(booking);
            return true;
        }).orElse(false);
    }

}
