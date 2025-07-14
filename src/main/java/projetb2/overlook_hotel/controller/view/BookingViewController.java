package projetb2.overlook_hotel.controller.view;

import java.util.Optional;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import projetb2.overlook_hotel.model.Booking;
import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.service.HotelUserService;
import projetb2.overlook_hotel.service.BookingService;

@Controller
@RequestMapping("/view/booking")
@SessionAttributes({ "adultsCount", "childrenCount", "fragmentPath", "fragmentName" })
public class BookingViewController {

    private final BookingService bookingService;
    private final HotelUserService hotelUserService;

    public BookingViewController(BookingService bookingService, HotelUserService hotelUserService) {
        this.bookingService = bookingService;
        this.hotelUserService = hotelUserService;
    }

    // Initialize default attributes used in Thymeleaf fragments
    @ModelAttribute
    public void initFragmentDefaults(Model model) {
        if (!model.containsAttribute("fragmentPath")) {
            model.addAttribute("fragmentPath", "fragments/home");
        }
        if (!model.containsAttribute("fragmentName")) {
            model.addAttribute("fragmentName", "fgt-home");
        }
        if (!model.containsAttribute("adultsCount")) {
            model.addAttribute("adultsCount", 2);
        }
        if (!model.containsAttribute("childrenCount")) {
            model.addAttribute("childrenCount", 0);
        }
    }

    @GetMapping("/process")
    public String processSearch(
        @RequestParam int adultCount,
        @RequestParam int childCount,
        Model model) {
        model.addAttribute("adultsCount", adultCount);
        model.addAttribute("childrenCount", childCount);
        return "layout/connectedLayout";
    }

    @GetMapping("/past-booking")
    public String showPastBooking(
        Model model,
        @AuthenticationPrincipal UserDetails currentUser) {
            Optional<HotelUser> userOpt = hotelUserService.findByEmail(currentUser.getUsername());
            model.addAttribute("fragmentPath", "fragments/past-booking");
            model.addAttribute("fragmentName", "fgt-past-booking");

            if (userOpt.isPresent()) {
                HotelUser user = userOpt.get();
                model.addAttribute("user", user);
                model.addAttribute("hotelUser", user);

                List<Booking> pastBookings = bookingService.getPastBookingsForCurrentUser(user.getId());
                model.addAttribute("pastBookings", pastBookings);
            } else {
                model.addAttribute("pastBookings", List.of());
            }
        return "layout/connectedLayout";
    }

    @GetMapping("/loyalty-points")
    public String showLoyaltyPoints(
        Model model,
        @AuthenticationPrincipal UserDetails currentUser) {

        Optional<HotelUser> userOpt = hotelUserService.findByEmail(currentUser.getUsername());

        model.addAttribute("fragmentPath", "fragments/past-booking");
        model.addAttribute("fragmentName", "fgt-past-booking");

        if (userOpt.isPresent()) {
            HotelUser user = userOpt.get();
            model.addAttribute("user", user);

            int loyaltyPoints = bookingService.calculateLoyaltyPoints(user.getId());
            model.addAttribute("loyaltyPoints", loyaltyPoints);
        }
        return "layout/connectedLayout";
    }
}
