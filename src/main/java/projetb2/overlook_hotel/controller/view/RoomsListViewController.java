package projetb2.overlook_hotel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.List;

import projetb2.overlook_hotel.service.RoomService;
import projetb2.overlook_hotel.model.Room;

@Controller
public class RoomsListViewController {

    private final RoomService roomService;

    public RoomsListViewController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms-list")
    public String showRoomForm(Model model) {
        List<Room> allRooms = roomService.getAllRooms();
        List<Room> onlyRooms = allRooms.stream()
        .filter(room -> "Room".equalsIgnoreCase(room.getAccommodationType()))
        .toList();

        model.addAttribute("rooms", onlyRooms);
        model.addAttribute("fragmentPath", "fragments/rooms_list.html");
        model.addAttribute("fragmentName", "fgt-rooms-list");

        return "layout/connectedLayout";
    }

    @GetMapping("/rooms-search")
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
    model.addAttribute("fragmentPath", "fragments/rooms_list.html");
    model.addAttribute("fragmentName", "fgt-rooms-list");

    return "layout/connectedLayout";
}
}
