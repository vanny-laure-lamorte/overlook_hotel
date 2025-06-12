package projetb2.overlook_hotel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// Controller for managing the booking search view
@Controller
@RequestMapping("/view/search")
@SessionAttributes({ "adultsCount", "childrenCount", "fragmentPath", "fragmentName" })
public class BookingSearchViewController {

    // Initialize default attributes used in Thymeleaf fragments
    @ModelAttribute
    public void initFragmentDefaults(Model model) {
        if (!model.containsAttribute("fragmentPath")) {
            model.addAttribute("fragmentPath", "fragments/test");
        }
        if (!model.containsAttribute("fragmentName")) {
            model.addAttribute("fragmentName", "home");
        }
        if (!model.containsAttribute("adultsCount")) {
            model.addAttribute("adultsCount", 2);
        }
        if (!model.containsAttribute("childrenCount")) {
            model.addAttribute("childrenCount", 0);
        }
    }

    @GetMapping("/process")
    public String processSearch(@RequestParam int adultCount,
                                @RequestParam int childCount,
                                Model model) {
        model.addAttribute("adultsCount", adultCount);
        model.addAttribute("childrenCount", childCount);
        return "layout/connectedLayout";
    }
}
