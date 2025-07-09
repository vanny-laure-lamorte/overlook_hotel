package projetb2.overlook_hotel.controller.api;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import projetb2.overlook_hotel.service.BookingService;
import projetb2.overlook_hotel.model.BookingStatus;

@Controller
@RequestMapping("/api/booking")
public class BookingApiController {

    private final BookingService bookingService;

    public BookingApiController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /*
     * Accepts a booking by its ID.
     * If the booking is successfully accepted, it redirects to the bookings page
     * with a success message.
     * If the booking cannot be accepted (e.g., not in PENDING status), it redirects
     * with an error message.
     */
    @PostMapping("/accept")
    public RedirectView acceptBooking(@RequestParam("bookingId") Long bookingId,
            RedirectAttributes redirectAttributes) {
        boolean result = bookingService.acceptBooking(bookingId);
        redirectAttributes.addFlashAttribute("message", result ? "Booking accepted" : "Could not accept booking");
        return new RedirectView("/admin/bookings");
    }

    /*
     * Declines a booking by its ID.
     * If the booking is successfully declined, it redirects to the bookings page
     * with a success message.
     * If the booking cannot be declined (e.g., not in PENDING status), it redirects
     * with an error message.
     */
    @PostMapping("/decline")
    public RedirectView declineBooking(@RequestParam("bookingId") Long bookingId,
            RedirectAttributes redirectAttributes) {
        boolean result = bookingService.declineBooking(bookingId);
        redirectAttributes.addFlashAttribute("message", result ? "Booking declined" : "Could not decline booking");
        System.out.println("\n\n\nDeclining booking with ID: " + bookingId + "\n\n\n");
        return new RedirectView("/admin/bookings");
    }

    /*
     * Edits a booking by its ID.
     * Updates the booking with the provided details and redirects to the bookings
     * page with a success or error message.
     */
    @PostMapping("/edit")
    public RedirectView editBooking(@RequestParam Long bookingId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate arrivingDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
            @RequestParam(required = false) BookingStatus bookingStatus,
            RedirectAttributes redirectAttributes) {
        boolean result = bookingService.updateBooking(bookingId, arrivingDate, departureDate, bookingStatus);
        redirectAttributes.addFlashAttribute("message", result ? "Booking updated" : "Could not update booking");
        return new RedirectView("/admin/bookings");
    }
}