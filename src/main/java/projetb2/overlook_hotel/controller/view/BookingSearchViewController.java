package projetb2.overlook_hotel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// Controller for managing the booking search view
@Controller
@RequestMapping("/view/search")
@SessionAttributes({ "adultsCount", "childrenCount", "fragmentPath", "fragmentName" })
public class BookingSearchViewController {

    // Initialize default path and name for the fragment if not already set
    @ModelAttribute
    public void initFragmentDefaults(Model model) {
        if (!model.containsAttribute("fragmentPath")) {
            model.addAttribute("fragmentPath", "fragments/test");
        }
        if (!model.containsAttribute("fragmentName")) {
            model.addAttribute("fragmentName", "home");
        }
    }

    // Method to increment the number of adults or children for reservation
    @PostMapping("/increment-rooms")
    public String incrementRoomNumber(@RequestParam String roomId,
            @RequestParam String type,
            @ModelAttribute("adultsCount") int adultsCount,
            @ModelAttribute("childrenCount") int childrenCount,
            Model model) {
        if (type.equals("adults")) {
            model.addAttribute("adultsCount", adultsCount + 1);
        } else if (type.equals("children")) {
            model.addAttribute("childrenCount", childrenCount + 1);
        }
        return "layout/connectedLayout";
    }

    // Method to decrement the number of adults or children for reservation
    @PostMapping("/decrement-rooms")
    public String decrementRoomNumber(@RequestParam String roomId,
            @RequestParam String type,
            @ModelAttribute("adultsCount") int adultsCount,
            @ModelAttribute("childrenCount") int childrenCount,
            Model model) {
        if (type.equals("adults")) {
            model.addAttribute("adultsCount", Math.max(0, adultsCount - 1));
        } else if (type.equals("children")) {
            model.addAttribute("childrenCount", Math.max(0, childrenCount - 1));
        }
        return "layout/connectedLayout";
    }
}