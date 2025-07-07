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
        return roomRepository.findAll();
    }

    /**
     * Retrieves a room by its ID.
     * @param id
     */
    public Room getRoomById(Integer id) {
        return roomRepository.findById(id).orElse(null);
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
}