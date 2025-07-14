package projetb2.overlook_hotel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MeetingRoomViewController {

    /*
     * Handles the meeting room page view.
     * Displays the meeting fragment in the connected layout.
     */
    @GetMapping("/view/meeting-room")
    public String showMeetingRoom(Model model) {
        model.addAttribute("fragmentPath", "fragments/meeting-room.html");
        model.addAttribute("fragmentName", "fgt-meeting-room");
        return "layout/connectedLayout";
    }
}
