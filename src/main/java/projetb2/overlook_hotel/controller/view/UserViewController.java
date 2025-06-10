package projetb2.overlook_hotel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

// Test controller for the home page
@Controller
public class UserViewController {
    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        model.addAttribute("fragmentPath", "fragments/test");
        model.addAttribute("fragmentName", "home");
        session.setAttribute("adultsCount", 2);
        session.setAttribute("childrenCount", 0);
        return "layout/connectedLayout";
    }
}