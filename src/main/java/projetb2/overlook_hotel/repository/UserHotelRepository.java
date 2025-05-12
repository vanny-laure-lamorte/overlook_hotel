package projetb2.overlook_hotel.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import projetb2.overlook_hotel.model.UserHotel;

@Repository
public interface UserHotelRepository extends JpaRepository<UserHotel, Long> {
    Optional<UserHotel> findByEmail(String email);
}