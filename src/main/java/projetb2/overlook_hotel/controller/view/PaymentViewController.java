package projetb2.overlook_hotel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentViewController {

    /*
     * Handles the payment page view.
     */
    @GetMapping("/payment")
    public String showHome(Model model) {
        model.addAttribute("fragmentPath", "fragments/payment.html");
        model.addAttribute("fragmentName", "fgt-payment");
        return "layout/connectedLayout";
    }
}
