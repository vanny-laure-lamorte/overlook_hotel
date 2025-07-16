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
import projetb2.overlook_hotel.service.FeedbackService;

@RestController
@RequestMapping("/api/customers")
public class CustomersApiController {

    private final HotelUserService hotelUserService;
    private final BookingService bookingService;
    private final FeedbackService feedbackService;

    public CustomersApiController(HotelUserService hotelUserService, BookingService bookingService, FeedbackService feedbackService) {
        this.hotelUserService = hotelUserService;
        this.bookingService = bookingService;
        this.feedbackService = feedbackService;
    }

    /**
     * Adds a new customer.
     * If the customer is successfully added, it redirects to the customers page with a success message.
     * If the customer cannot be added (e.g., email already exists), it redirects with an error message.
     *
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @param dob The date of birth of the customer
     * @param address The address of the customer
     * @param role The role of the customer
     * @return RedirectView to the customers page with a success or error message
     */
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

    /**
     * Deletes a customer by their ID.
     * If the customer is successfully deleted, it redirects to the customers page with a success message.
     * If the customer cannot be deleted (e.g., does not exist), it redirects with an error message.
     *
     * @param id The ID of the customer to delete
     * @return RedirectView to the customers page with a success or error message
     */
    @PostMapping("/delete")
    public RedirectView deleteCustomer(@RequestParam("customerId") Integer id) {
        bookingService.cancelBookingsByUserId(id);
        feedbackService.deleteFeedbackByUserId(id);
        HotelUser existingUser = hotelUserService.findById(id);
        if (existingUser != null) {
            hotelUserService.deleteUser(existingUser);
        }
        return new RedirectView("/view/dashboard/customers");
    }

}
