package projetb2.overlook_hotel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import projetb2.overlook_hotel.model.Booking;
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
}
