package projetb2.overlook_hotel.controller.view;

import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.service.BookingService;
import projetb2.overlook_hotel.service.HotelUserService;

@RequestMapping("/view/loyalty")
@Controller
public class LoyaltyViewController {
    private final BookingService bookingService;
    private final HotelUserService hotelUserService;

    public LoyaltyViewController(BookingService bookingService, HotelUserService hotelUserService) {
        this.bookingService = bookingService;
        this.hotelUserService = hotelUserService;
    }

    /**
     * Display the loyalty points for the current user.
     */
    @GetMapping("/total-points")
    public String showLoyaltyPoints(
        Model model,
        @AuthenticationPrincipal UserDetails currentUser) {

        Optional<HotelUser> userOpt = hotelUserService.findByEmail(currentUser.getUsername());

        model.addAttribute("fragmentPath", "fragments/loyalty.html");
        model.addAttribute("fragmentName", "fgt-loyalty");

        if (userOpt.isPresent()) {
            HotelUser user = userOpt.get();
            model.addAttribute("user", user);

            int loyaltyPoints = bookingService.calculateLoyaltyPoints(user.getId());
            System.out.println("Loyalty points for user " + user.getId() + ": " + loyaltyPoints);
            model.addAttribute("loyaltyPoints", loyaltyPoints);
        }
        return "layout/connectedLayout";
    }

    /**
     * Display the loyalty points for the current user.
     */
    @GetMapping("/level")
    public String showLoyaltyLevel(
        // @PathVariable Integer userId
        // @AuthenticationPrincipal UserDetails currentUser,
        Model model
        ) {

        // Optional<HotelUser> userOpt = hotelUserService.findByEmail(currentUser.getUsername());

        model.addAttribute("fragmentPath", "fragments/loyalty-level.html");
        model.addAttribute("fragmentName", "fgt-loyalty");

        // if (userOpt.isPresent()) {
        //     HotelUser user = userOpt.get();
        //     model.addAttribute("user", user);

        //     String loyaltyLevel = bookingService.calculateLoyaltyLevel(user.getId());
        //     System.out.println("LOYALTYYYYY " + user.getId() + ": " + loyaltyLevel);
        //     model.addAttribute("loyalty-level", loyaltyLevel);
        // }
        return "layout/connectedLayout";
    }
}
