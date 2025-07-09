package projetb2.overlook_hotel.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import projetb2.overlook_hotel.service.BookingService;

@Controller
@RequestMapping("/api/booking")
public class BookingApiController {

    private final BookingService bookingService;

    public BookingApiController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/accept")
    public RedirectView acceptBooking(@RequestParam("bookingId") Long bookingId, RedirectAttributes redirectAttributes) {
        boolean result = bookingService.acceptBooking(bookingId);
        redirectAttributes.addFlashAttribute("message", result ? "Booking accepted" : "Could not accept booking");
        return new RedirectView("/admin/bookings");
    }

    @PostMapping("/decline")
    public RedirectView declineBooking(@RequestParam("bookingId") Long bookingId, RedirectAttributes redirectAttributes) {
        boolean result = bookingService.declineBooking(bookingId);
        redirectAttributes.addFlashAttribute("message", result ? "Booking declined" : "Could not decline booking");
        System.out.println("\n\n\nDeclining booking with ID: " + bookingId + "\n\n\n");
        return new RedirectView("/admin/bookings");
    }
}