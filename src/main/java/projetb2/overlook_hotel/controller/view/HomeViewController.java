package projetb2.overlook_hotel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {
    @GetMapping("/home")
    public String showHome(Model model) {
        model.addAttribute("fragmentPath", "fragments/home.html");
        model.addAttribute("fragmentName", "fgt-home");
        return "layout/connectedLayout";
    }
}