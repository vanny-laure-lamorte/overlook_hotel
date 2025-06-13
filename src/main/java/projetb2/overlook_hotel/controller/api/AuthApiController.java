package projetb2.overlook_hotel.controller.api;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.security.CustomUserDetails;

@Controller
public class AuthApiController {

    @GetMapping("/auth")
    public String loginRedirect(@RequestParam(value = "error", required = false) String error,
                                 Model model) {
        if (error != null) {
            model.addAttribute("loginError", "Invalid credentials or unauthorized access.");
            System.out.println("Login error: " + error);
        } else {
            System.out.println("Login successful or no error.");
        }

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