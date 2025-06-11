package projetb2.overlook_hotel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserViewController {
    /**
     * Displays the home page with optional modal and tab selection.
     *
     * @param modal         Whether to show a modal (optional)
     * @param tab           The selected tab (optional)
     * @param loginError    Error message for login (optional)
     * @param registerError Error message for registration (optional)
     * @param model         Model to add attributes for the view
     * @return The name of the view to render
     */
    @GetMapping("/")
    public String home(
            @RequestParam(required = false) Boolean modal,
            @RequestParam(required = false) String tab,
            @RequestParam(required = false) String loginError,
            @RequestParam(required = false) String registerError,
            Model model) {

        if (Boolean.TRUE.equals(modal))
            model.addAttribute("showModal", true);
        if (tab != null)
            model.addAttribute("selectedTab", tab);
        if (loginError != null)
            model.addAttribute("loginError", loginError);
        if (registerError != null)
            model.addAttribute("registerError", registerError);

        model.addAttribute("fragmentPath", "fragments/test");
        model.addAttribute("fragmentName", "home");
        return "layout/connectedLayout";
    }
}