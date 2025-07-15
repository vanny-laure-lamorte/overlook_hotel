package projetb2.overlook_hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetb2.overlook_hotel.model.Room;
import projetb2.overlook_hotel.repository.RoomRepository;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    /**
     * Retrieves all rooms from the repository.
     * @return a List<Room> containing all rooms.
     */
    public List<Room> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        for (Room room : rooms) {
            room.setRoomTitleLabel(mapRoomTitle(room.getRoomTitle()));
        }
        return rooms;
    }

    /*
     * Maps room title integers to their corresponding string labels.
     */
    public String mapRoomTitle(int roomTitle) {
        return switch (roomTitle) {
            case 1 -> "Standard Queen Room";
            case 2 -> "Superior Sea View";
            case 3 -> "Basic Single Room";
            case 4 -> "Family Room";
            case 5 -> "Deluxe King Room";
            case 6 -> "Cozy City Room";
            case 7 -> "Conference Room";
            case 8 -> "Large Meeting Room";
            case 9 -> "Spa with Jacuzzi";
            case 10 -> "Spa Sauna & Massage";
            default -> "Unknown Room Type";
        };
    }

    /**
     * Retrieves a room by its ID.
     * @param id
     */
    public Room getRoomById(Integer id) {
        return roomRepository.findById(id).orElse(null);
    }

    /**
     * Finds rooms that can accommodate a minimum number of guests.
     * @param minCapacity the minimum capacity required for the rooms.
     * @return a List<Room> containing rooms that meet the capacity requirement.
     */
    public List<Room> findRoomsByMinimumCapacity(int minCapacity) {
        return roomRepository.findByCapacityGreaterThanEqual(minCapacity);
    }

    /**
     * Creates a new room in the repository.
     */
    public void createRoom() {
    }

    /**
     * Updates an existing room in the repository.
     */
    public void updateRoom() {
    }

    /**
     * Deletes a room from the repository.
     */
    public void deleteRoom() {
    }


    public List<Room> getRoomsByTitle(int roomTitle) {
        List<Room> rooms = roomRepository.findByRoomTitle(roomTitle);
        rooms.forEach(room -> room.setRoomTitleLabel(mapRoomTitle(room.getRoomTitle())));
        return rooms;
    }

}