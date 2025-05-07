package projetb2.overlook_hotel.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import projetb2.overlook_hotel.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>  {

}

