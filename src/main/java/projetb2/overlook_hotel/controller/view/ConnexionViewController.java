package projetb2.overlook_hotel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class ConnexionViewController {

    @GetMapping("/test")
    public String home(Model model) {
        model.addAttribute("fragmentPath", "fragments/test.html");
        model.addAttribute("fragmentName", "test");
        return "layout/connectedLayout";
    }

    @GetMapping("/login")
    public String afficherConnexionParDefaut(Model model) {
        model.addAttribute("selectedTab", "customer");
        model.addAttribute("fragmentPath", "fragments/connexion-modal.html");
        model.addAttribute("fragmentName", "connexion-modal");
        return "layout/connectedLayout";
    }

    @PostMapping("/change-tab")
    public String changerOnglet(@RequestParam("tab") String tab, Model model) {
        model.addAttribute("selectedTab", tab);
        model.addAttribute("fragmentPath", "fragments/connexion-modal.html");
        model.addAttribute("fragmentName", "connexion-modal");
        return "layout/connectedLayout";
    }
}
