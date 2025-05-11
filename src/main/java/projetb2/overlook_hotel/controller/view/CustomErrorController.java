package projetb2.overlook_hotel.controller.view;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(Model model) {
        // Récupérer le code d'état HTTP
        Object status = RequestContextHolder.currentRequestAttributes()
                .getAttribute("javax.servlet.error.status_code", RequestAttributes.SCOPE_REQUEST);
        Integer statusCode = (status != null) ? Integer.valueOf(status.toString()) : 500;

        model.addAttribute("fragmentPath", "fragments/errors.html");
        model.addAttribute("fragmentName", "errors");
        model.addAttribute("fragmentNumber", statusCode.toString());

        return "layout/connectedLayout";
    }
}
