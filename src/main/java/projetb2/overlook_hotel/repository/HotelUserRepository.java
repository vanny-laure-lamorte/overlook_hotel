package projetb2.overlook_hotel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projetb2.overlook_hotel.model.HotelUser;

@Repository
public interface HotelUserRepository extends JpaRepository<HotelUser, Integer> {
    /**
     * Finds a HotelUser by their email.
     *
     * @param email the email of the HotelUser
     * @return an Optional containing the HotelUser if found, or empty if not found
     */
    Optional<HotelUser> findByEmail(String email);

    /**
     * Finds all HotelUsers with a specific role.
     *
     * @return a list of HotelUsers
     */
    List<HotelUser> findByRole_RoleName(String roleName);

    boolean existsByEmail(String email);

}