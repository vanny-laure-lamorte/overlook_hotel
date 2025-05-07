package projetb2.overlook_hotel.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import projetb2.overlook_hotel.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}