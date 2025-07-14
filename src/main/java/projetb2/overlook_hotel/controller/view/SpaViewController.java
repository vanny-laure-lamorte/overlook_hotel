package projetb2.overlook_hotel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaViewController {
    /*
     * Handles the spa page view.
     */
    @GetMapping("/all-spa")
    public String showSpaPage(Model model) {
        model.addAttribute("fragmentPath", "fragments/spa.html");
        model.addAttribute("fragmentName", "fgt-spa");
        return "layout/connectedLayout";
    }
}


