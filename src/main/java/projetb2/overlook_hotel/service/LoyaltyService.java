package projetb2.overlook_hotel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projetb2.overlook_hotel.model.Booking;
import projetb2.overlook_hotel.repository.HotelUserRepository;

@Service
public class LoyaltyService {

    private final BookingService bookingService;
    private final HotelUserRepository hotelUserRepo;

    public LoyaltyService(BookingService bookingService, HotelUserRepository hotelUserRepo) {
        this.bookingService = bookingService;
        this.hotelUserRepo = hotelUserRepo;
    }

    /*
     * Update loyalty points in hotel_User table
     */
    @Transactional
    public void updateLoyaltyForUser(Integer userId) {
        List<Booking> pastBookings = bookingService.getPastBookingsForCurrentUser(userId);

        int bookingCount = pastBookings.size();

        hotelUserRepo.findById(userId).ifPresent(user -> {
            user.setLoyalty(bookingCount);
            hotelUserRepo.save(user);
        });
    }
}
