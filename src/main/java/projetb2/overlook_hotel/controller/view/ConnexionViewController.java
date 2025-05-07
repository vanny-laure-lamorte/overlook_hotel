package projetb2.overlook_hotel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class ConnexionViewController {

    @GetMapping("/test")
    public String home(Model model) {
        model.addAttribute("fragmentPath", "fragments/test.html");
        model.addAttribute("fragmentName", "test");
        return "layout/connectedLayout";
    }

    @GetMapping("/connexion")
    public String changerContenu(Model model) {
        model.addAttribute("fragmentPath", "fragments/connexion-modal.html");
        model.addAttribute("fragmentName", "connexion-modal");
        return "layout/connectedLayout";
    }

}
