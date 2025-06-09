package projetb2.overlook_hotel.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import projetb2.overlook_hotel.model.HotelUser;

@Repository
public interface HotelUserRepository extends JpaRepository<HotelUser, Long> {
    Optional<HotelUser> findByEmail(String email);
}