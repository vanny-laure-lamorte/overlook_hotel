package projetb2.overlook_hotel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

import projetb2.overlook_hotel.service.RoomService;
import projetb2.overlook_hotel.model.Room;

@RequestMapping("/view/rooms")
@Controller
public class RoomsViewController {

    private final RoomService roomService;
    public RoomsViewController(RoomService roomService) {
        this.roomService = roomService;
    }

    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    @GetMapping("/all")
    public String showRoomForm(Model model) {
        List<Room> allRooms = roomService.getAllRooms();
        List<Room> onlyRooms = allRooms.stream()
        .filter(room -> "Room".equalsIgnoreCase(room.getAccommodationType()))
        .toList();

        System.out.println();

        model.addAttribute("rooms", onlyRooms);
        model.addAttribute("fragmentPath", "fragments/all-rooms.html");
        model.addAttribute("fragmentName", "fgt-all-rooms");

        return "layout/connectedLayout";
    }

    @GetMapping("/filter")
    public String searchRooms(
            @RequestParam(name = "adultCount", defaultValue = "2") int adults,
            @RequestParam(name = "childCount", defaultValue = "0") int children,
            Model model) {

        int totalGuests = adults + children;
        List<Room> matchingRooms = roomService.findRoomsByMinimumCapacity(totalGuests);
        List<Room> onlyRooms = matchingRooms.stream()
            .filter(room -> "Room".equalsIgnoreCase(room.getAccommodationType()))
            .toList();

        model.addAttribute("rooms", onlyRooms);
        model.addAttribute("fragmentPath", "fragments/all-rooms.html");
        model.addAttribute("fragmentName", "fgt-all-rooms");

        return "layout/connectedLayout";
    }

    @GetMapping("/{roomTitleId}")
    public String getRoomByTitle(@PathVariable("roomTitleId") int roomTitleId, Model model) {
        List<Room> rooms = roomService.getRoomsByTitle(roomTitleId);

        if (rooms.isEmpty()) {
            model.addAttribute("error", "No rooms found for the given title.");
            return "layout/connectedLayout";
        }

        model.addAttribute("rooms", rooms);
        model.addAttribute("fragmentPath", "fragments/all-rooms.html");
        model.addAttribute("fragmentName", "fgt-all-rooms");
        return "layout/connectedLayout";
    }
}
