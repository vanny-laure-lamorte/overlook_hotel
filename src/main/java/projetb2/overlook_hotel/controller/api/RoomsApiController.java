package projetb2.overlook_hotel.controller.api;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projetb2.overlook_hotel.model.Room;
import projetb2.overlook_hotel.service.RoomService;

@RestController
@RequestMapping("/api/rooms")
public class RoomsApiController {

    private final RoomService roomService;

    public RoomsApiController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/all-rooms")
    public List<Room> getAllRooms() {
        for (Room room : roomService.getAllRooms()) {
            System.out.println(room);
        }
        return roomService.getAllRooms();
    }

}
