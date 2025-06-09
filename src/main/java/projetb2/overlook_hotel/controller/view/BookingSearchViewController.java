package projetb2.overlook_hotel.controller.view;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.ui.Model;
@Controller
@RequestMapping("/")
public class BookingSearchViewController {
     private final Map<String, Integer> roomCounts = new HashMap<>();

    @GetMapping("/booking/search")
    public String showBookingForm(Model model) {
        // Set default values if empty
        if (roomCounts.isEmpty()) {
            roomCounts.put("adults-1", 2);
            roomCounts.put("children-1", 0);
        }
        model.addAttribute("roomCounts", roomCounts);
        return "layout/connectedLayout";
    }

}
