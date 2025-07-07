package projetb2.overlook_hotel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

@Controller
public class ProfilViewController {

    @GetMapping("/profil")
    public String showUserHotelProfil(Model model) {
        model.addAttribute("fragmentPath", "fragments/profil.html");
        model.addAttribute("fragmentName", "fgt-profil");
        return "layout/connectedLayout";
    }
}



