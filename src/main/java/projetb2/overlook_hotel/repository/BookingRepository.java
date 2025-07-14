package projetb2.overlook_hotel.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import projetb2.overlook_hotel.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    /**
     * Finds all bookings made by a user with the specified ID.
     *
     * @param userId the ID of the user
     * @return a list of bookings made by the user
     */
    @Query("SELECT b FROM Booking b " +
            "WHERE b.user.id = :userId " +
            "AND b.departureDate < :today " +
            "ORDER BY b.departureDate DESC")
    List<Booking> findPastBookingsByUserId(Integer userId, LocalDate today);

    List<Booking> findByUser_Id(Integer userId);
}