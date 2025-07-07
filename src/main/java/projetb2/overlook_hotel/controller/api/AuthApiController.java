package projetb2.overlook_hotel.controller.api;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import projetb2.overlook_hotel.security.CustomUserDetails;

@Controller
public class AuthApiController {

    @GetMapping("/auth")
    public String loginRedirect(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {
        if (error != null) {
            model.addAttribute("loginError", "Invalid credentials or unauthorized access.");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "Sucessfully disconnected.");
        }

        model.addAttribute("fragmentPath", "fragments/test");
        model.addAttribute("fragmentName", "home");

        return "layout/connectedLayout";
    }

}