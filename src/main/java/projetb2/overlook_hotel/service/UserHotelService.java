package projetb2.overlook_hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.repository.HotelUserRepository;

import java.util.List;

@Service
public class UserHotelService {

    @Autowired
    private HotelUserRepository userHotelRepository;

    public List<HotelUser> getAllUsers() {
        return userHotelRepository.findAll();
    }
}
