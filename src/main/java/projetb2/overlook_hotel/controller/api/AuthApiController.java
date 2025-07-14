package projetb2.overlook_hotel.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/api/auth")
public class AuthApiController {
    private final projetb2.overlook_hotel.service.HotelUserService hotelUserService;

    AuthApiController(projetb2.overlook_hotel.service.HotelUserService hotelUserService) {
        this.hotelUserService = hotelUserService;
    }

    @PostMapping("/register")
    public RedirectView addCustomer(
            @RequestParam("registerFullName") String fullName,
            @RequestParam("registerEmail") String email,
            @RequestParam("registerPassword") String password) {

        try {
            String[] nameParts = fullName.trim().split("\\s+", 2);
            String firstName = nameParts[0];
            String lastName = nameParts.length > 1 ? nameParts[1] : "";

            hotelUserService.addUser(firstName, lastName, email, password);
            return new RedirectView("/auth?registerSuccess=true");

        } catch (IllegalArgumentException e) {
            return new RedirectView("/auth?registerError=" + e.getMessage());
        }
    }
}
