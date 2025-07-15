package projetb2.overlook_hotel.controller.view;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import projetb2.overlook_hotel.model.Booking;
import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.service.BookingService;
import projetb2.overlook_hotel.service.HotelUserService;
import projetb2.overlook_hotel.service.LoyaltyService;

@RequestMapping("/view/loyalty")
@Controller
public class LoyaltyViewController {

    private final LoyaltyService loyaltyService;
    private final BookingService bookingService;
    private final HotelUserService hotelUserService;

    public LoyaltyViewController(BookingService bookingService, HotelUserService hotelUserService, LoyaltyService loyaltyService) {
        this.bookingService = bookingService;
        this.hotelUserService = hotelUserService;
        this.loyaltyService = loyaltyService;
    }

    /**
     * Display the loyalty points for the current user.
     */
    @GetMapping("/level")
    public String showLoyaltyLevel(
        @AuthenticationPrincipal UserDetails currentUser,
        Model model
        ) {
            Optional<HotelUser> userOpt = hotelUserService.findByEmail(currentUser.getUsername());
            HotelUser user = userOpt.get();

            List<Booking> pastBookings = bookingService.getPastBookingsForCurrentUser(user.getId());
            int loyaltyPoint = pastBookings.size();

            model.addAttribute("loyaltyPoint", loyaltyPoint);
            model.addAttribute("fragmentPath", "fragments/loyalty-level.html");
            model.addAttribute("fragmentName", "fgt-loyalty");
        return "layout/connectedLayout";
    }

    @GetMapping("/update")
    @ResponseBody
    public String updateAndShowLoyalty(
        @AuthenticationPrincipal UserDetails currentUser,
        Model model
        ) {

        Optional<HotelUser> userOpt = hotelUserService.findByEmail(currentUser.getUsername());
        if (userOpt.isPresent()) {
            HotelUser hotelUser = userOpt.get();
           loyaltyService.updateLoyaltyForUser(hotelUser.getId());
        }
        return "layout/connectedLayout";
    }
}
