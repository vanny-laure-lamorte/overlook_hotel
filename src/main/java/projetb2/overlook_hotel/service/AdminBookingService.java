package projetb2.overlook_hotel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetb2.overlook_hotel.model.Booking;
import projetb2.overlook_hotel.repository.BookingRepository;

@Service
public class AdminBookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public AdminBookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void createBooking() {
    }

    public void modifyBooking() {
    }

    public void deleteBooking() {
    }

    public void getBookingById() {
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

}
