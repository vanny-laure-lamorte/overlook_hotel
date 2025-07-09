package projetb2.overlook_hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.repository.HotelUserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserHotelService {

    @Autowired
    private HotelUserRepository userHotelRepository;

    public List<HotelUser> getAllUsers() {
        return userHotelRepository.findAll();
    }

    public HotelUser saveUser(HotelUser user) {
        return userHotelRepository.save(user);
    }

    public HotelUser updateUser(HotelUser user) {
        return userHotelRepository.save(user);
    }

    public Optional<HotelUser> findByEmail(String email) {
        return userHotelRepository.findByEmail(email);
    }

    public void deleteUser(HotelUser user) {
        userHotelRepository.delete(user);
    }

    public List<HotelUser> findByRole(String roleName) {
        return userHotelRepository.findByRole_RoleName(roleName);
    }

    public HotelUser findById(Integer id) {
        return userHotelRepository.findById(id).orElse(null);
    }
}
