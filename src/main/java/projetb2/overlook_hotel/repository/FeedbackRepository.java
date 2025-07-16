package projetb2.overlook_hotel.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projetb2.overlook_hotel.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    List<Feedback> findByHotelUser_Id(Integer userId);


    @Query("SELECT f FROM Feedback f " +
       "JOIN f.booking b " +
       "JOIN b.room r " +
       "WHERE r.roomTitle = :roomTitle")
    List<Feedback> findByRoomTitle(@Param("roomTitle") Integer roomTitle);

    Long countByBookingRoomRoomTitle(Integer roomTitle);
}
