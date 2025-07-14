package projetb2.overlook_hotel.controller.api;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.service.HotelUserService;
import projetb2.overlook_hotel.service.BookingService;

@RestController
@RequestMapping("/api/customers")
public class CustomersApiController {

    private final HotelUserService hotelUserService;
    private final BookingService bookingService;

    public CustomersApiController(HotelUserService hotelUserService, BookingService bookingService) {
        this.hotelUserService = hotelUserService;
        this.bookingService = bookingService;
    }

    @PostMapping("/edit")
    public RedirectView editCustomer(
            @RequestParam("id") Integer id,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("dob") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dob,
            @RequestParam("address") String address,
            @RequestParam("role") String role) {
        hotelUserService.updateUser(id, firstName, lastName, dob, address, role);
        return new RedirectView("/view/dashboard/customers");
    }

    @PostMapping("/delete")
    public RedirectView deleteCustomer(@RequestParam("customerId") Integer id) {
        bookingService.cancelBookingsByUserId(id);
        System.out.println("\n\n\n----- Booking cancelled -----\n\n\n");
        HotelUser existingUser = hotelUserService.findById(id);
        System.out.println("\n\n\n----- Existing User: " + existingUser + " -----\n\n\n");
        if (existingUser != null) {
            System.out.println("\n\n\nCustomer with ID " + existingUser.getFirstName() + " has been deleted.\n\n\n");
            hotelUserService.deleteUser(existingUser);
        }
        return new RedirectView("/view/dashboard/customers");
    }

}
