package projetb2.overlook_hotel.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.util.Optional;

import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.service.HotelUserService;

import org.springframework.ui.Model;

@Controller
public class ProfileViewController {

    @Autowired
    private HotelUserService hotelUserService;

    @GetMapping("/profile")
    public String showUserHotelProfile(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        Optional<HotelUser> userOpt = hotelUserService.findByEmail(currentUser.getUsername());

        if (userOpt.isPresent()) {
            model.addAttribute("user", userOpt.get());
        }

        model.addAttribute("fragmentPath", "fragments/profile.html");
        model.addAttribute("fragmentName", "fgt-profile");
        model.addAttribute("editMode", false);
        return "layout/connectedLayout";
    }

    @GetMapping("/profile/edit")
    public String showEditProfile(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        Optional<HotelUser> userOpt = hotelUserService.findByEmail(currentUser.getUsername());

        if (userOpt.isPresent()) {
            model.addAttribute("user", userOpt.get());
        }

        model.addAttribute("fragmentPath", "fragments/profile.html");
        model.addAttribute("fragmentName", "fgt-profile");
        model.addAttribute("editMode", true);
        return "layout/connectedLayout";
    }
}



