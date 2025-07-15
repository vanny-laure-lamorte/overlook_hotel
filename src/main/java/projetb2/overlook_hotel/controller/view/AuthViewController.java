package projetb2.overlook_hotel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthViewController {

    /**
     * Redirects to the login page with appropriate messages based on request parameters.
     *
     * @param error Optional error message for invalid credentials or unauthorized access.
     * @param logout Optional message indicating successful logout.
     * @param register Optional message indicating successful registration.
     * @param registerError Optional error message for registration failure.
     * @param model Model to add attributes for the view.
     * @return The name of the view to render.
     */
    @GetMapping("/auth")
    public String loginRedirect(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "registerSuccess", required = false) String register,
            @RequestParam(value = "registerError", required = false) String registerError,
            Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid credentials or unauthorized access.");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "Sucessfully disconnected.");
        }
        if (register != null) {
            model.addAttribute("registerSuccess", "Registration successful! Please log in.");
        }
        if (registerError != null) {
            model.addAttribute("registerError", "Registration failed: " + registerError);
        }
        model.addAttribute("fragmentPath", "fragments/home");
        model.addAttribute("fragmentName", "fgt-home");

        return "layout/connectedLayout";
    }
}
