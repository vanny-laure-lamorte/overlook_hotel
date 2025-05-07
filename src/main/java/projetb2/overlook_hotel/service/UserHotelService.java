package projetb2.overlook_hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetb2.overlook_hotel.model.UserHotel;
import projetb2.overlook_hotel.repository.UserHotelRepository;

import java.util.List;

@Service
public class UserHotelService {

    @Autowired
    private UserHotelRepository userHotelRepository;

    public List<UserHotel> getAllUsers() {
        return userHotelRepository.findAll();
    }
}
