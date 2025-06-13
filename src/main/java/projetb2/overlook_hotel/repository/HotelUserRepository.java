package projetb2.overlook_hotel.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import projetb2.overlook_hotel.model.HotelUser;


@Repository
public interface HotelUserRepository extends JpaRepository<HotelUser, Long> {
    /**
     * Finds a HotelUser by their email.
     *
     * @param email the email of the HotelUser
     * @return an Optional containing the HotelUser if found, or empty if not found
     */
    Optional<HotelUser> findByEmail(String email);
}