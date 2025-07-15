package projetb2.overlook_hotel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeViewController {

    @ModelAttribute
    public void initFragmentDefaults(Model model) {
        if (!model.containsAttribute("fragmentPath")) {
            model.addAttribute("fragmentPath", "fragments/home");
        }
        if (!model.containsAttribute("fragmentName")) {
            model.addAttribute("fragmentName", "fgt-home");
        }
        if (!model.containsAttribute("adultsCount")) {
            model.addAttribute("adultsCount", 2);
        }
        if (!model.containsAttribute("childrenCount")) {
            model.addAttribute("childrenCount", 0);
        }
    }

    /*
     * Handles the home page view.
     * Displays the home fragment in the connected layout.
     */
    @GetMapping("/")
    public String showHome(Model model) {
        model.addAttribute("fragmentPath", "fragments/home");
        model.addAttribute("fragmentName", "fgt-home");
        return "layout/connectedLayout";
    }
}