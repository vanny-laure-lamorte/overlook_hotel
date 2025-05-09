package projetb2.overlook_hotel.controller.api;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projetb2.overlook_hotel.model.Booking;
import projetb2.overlook_hotel.service.AdminBookingService;


@RestController
@RequestMapping("/api/admin")
public class AdminBookingApiController {

    private final AdminBookingService adminBookingService;

    public AdminBookingApiController(AdminBookingService adminBookingService) {
        this.adminBookingService = adminBookingService;
    }

    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        for (Booking booking : adminBookingService.getAllBookings()) {
            System.out.println(booking);
        }

        return adminBookingService.getAllBookings();
    }
}

