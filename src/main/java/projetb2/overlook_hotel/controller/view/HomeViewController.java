package projetb2.overlook_hotel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {
    @GetMapping("/")
    public String showHome(Model model) {
        model.addAttribute("fragmentPath", "fragments/home");
        model.addAttribute("fragmentName", "fgt-home");
        return "layout/connectedLayout";
    }
}