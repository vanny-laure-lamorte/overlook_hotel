package projetb2.overlook_hotel.controller.view;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.security.CustomUserDetails;

@Controller
public class UserViewController {
    /**
     * Displays the home page with optional modal and tab selection.
     * 
     * @param model Model to add attributes for the view
     * @return The name of the view to render
     */
    @GetMapping("/")
    public String showHome(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAuthenticated = auth != null && auth.isAuthenticated()
                && !(auth instanceof AnonymousAuthenticationToken);
        model.addAttribute("isAuthenticated", isAuthenticated);

        if (isAuthenticated && auth.getPrincipal() instanceof CustomUserDetails customUserDetails) {
            HotelUser user = customUserDetails.getUser();
            model.addAttribute("user", user);
        }

        model.addAttribute("fragmentPath", "fragments/test");
        model.addAttribute("fragmentName", "home");
        return "layout/connectedLayout";
    }
}