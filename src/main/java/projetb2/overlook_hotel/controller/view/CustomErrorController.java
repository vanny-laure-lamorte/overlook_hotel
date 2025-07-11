package projetb2.overlook_hotel.controller.view;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import projetb2.overlook_hotel.security.CustomUserDetails;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String fragmentNumber = "500";

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                fragmentNumber = "404";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                fragmentNumber = "403";
            }
        }

        // [!] Injecter manuellement user et isAuthenticated
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = auth != null && auth.isAuthenticated() &&
                !(auth instanceof AnonymousAuthenticationToken);
        model.addAttribute("isAuthenticated", isAuthenticated);
        if (isAuthenticated && auth.getPrincipal() instanceof CustomUserDetails details) {
            model.addAttribute("user", details.getUser());
        }

        model.addAttribute("fragmentPath", "fragments/errors.html");
        model.addAttribute("fragmentName", "errors");
        model.addAttribute("fragmentNumber", fragmentNumber);
        return "layout/connectedLayout";
    }
}
