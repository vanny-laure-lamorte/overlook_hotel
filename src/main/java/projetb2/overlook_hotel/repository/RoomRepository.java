package projetb2.overlook_hotel.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import projetb2.overlook_hotel.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    /*
     * Finds all rooms with a capacity greater than or equal to the specified value.
     * 
     */
    List<Room> findByCapacityGreaterThanEqual(int capacity);
}