package projetb2.overlook_hotel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

@Controller
public class ProfileViewController {

    @GetMapping("/profile")
    public String showUserHotelProfile(Model model) {
        model.addAttribute("fragmentPath", "fragments/profile.html");
        model.addAttribute("fragmentName", "fgt-profile");
        return "layout/connectedLayout";
    }
}



