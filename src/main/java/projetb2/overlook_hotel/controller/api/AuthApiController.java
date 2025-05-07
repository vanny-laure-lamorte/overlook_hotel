package projetb2.overlook_hotel.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/auth")
@Controller
public class AuthApiController {

    @PostMapping("/login")
    public String login() {
        // Logic for user login
        return "Login successful";
    }
    @PostMapping("/register")
    public String register() {
        // Logic for user registration
        return "Registration successful";
    }
}
