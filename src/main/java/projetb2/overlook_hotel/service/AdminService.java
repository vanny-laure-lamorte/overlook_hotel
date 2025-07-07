package projetb2.overlook_hotel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.repository.HotelUserRepository;

@Service
public class AdminService {

    @Autowired
    private HotelUserRepository userRepo;

    /**
     * Retrieves a list of all employees, including both regular employees and admins.
     *
     * @return List of HotelUser objects representing all employees
     */
    public List<HotelUser> getAllEmployees() {
        List<HotelUser> employees = userRepo.findByRole_RoleName("employee");
        List<HotelUser> admins = userRepo.findByRole_RoleName("admin");

        employees.addAll(admins);
        return employees;
    }
}
