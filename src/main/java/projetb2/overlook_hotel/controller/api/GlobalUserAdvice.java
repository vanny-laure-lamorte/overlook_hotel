package projetb2.overlook_hotel.controller.api;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import projetb2.overlook_hotel.security.CustomUserDetails;
import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.service.HotelUserService;

@ControllerAdvice
public class GlobalUserAdvice {

    private final HotelUserService hotelUserService;

    public GlobalUserAdvice(HotelUserService hotelUserService) {
        this.hotelUserService = hotelUserService;
    }

    @ModelAttribute
    public void addUserToModel(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = auth != null && auth.isAuthenticated()
                && !(auth instanceof AnonymousAuthenticationToken);

        model.addAttribute("isAuthenticated", isAuthenticated);

        if (isAuthenticated && auth.getPrincipal() instanceof CustomUserDetails details) {
            HotelUser user = details.getUser();
            int profileCompletion = hotelUserService.findProfileCompletionHotelUser(user);
            model.addAttribute("user", user);
            if (profileCompletion < 100) {
                model.addAttribute("profileCompletion", profileCompletion);
            }


        }
    }
}
