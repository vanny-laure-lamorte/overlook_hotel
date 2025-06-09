package projetb2.overlook_hotel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

@Controller
public class RoomsListViewController {

    @GetMapping("/rooms-list")
    public String showBookingForm(Model model) {
        model.addAttribute("fragmentPath", "fragments/rooms_list.html");
        model.addAttribute("fragmentName", "fgt-rooms-list");

        return "layout/connectedLayout";
    }
}
