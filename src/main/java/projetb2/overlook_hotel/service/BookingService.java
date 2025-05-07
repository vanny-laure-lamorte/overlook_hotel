package projetb2.overlook_hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetb2.overlook_hotel.repository.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Example method to create a new booking
    public void createBooking() {
        // Implementation goes here
    }

    // Example method to update an existing booking
    public void modifyBooking() {
        // Implementation goes here
    }

    // Example method to delete a booking
    public void deleteBooking() {
        // Implementation goes here
    }

    // Example method to retrieve a booking by ID
    public void getBookingById() {
        // Implementation goes here
    }

}
