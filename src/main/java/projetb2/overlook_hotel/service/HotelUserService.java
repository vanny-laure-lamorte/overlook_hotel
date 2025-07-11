package projetb2.overlook_hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.repository.HotelUserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HotelUserService {

    @Autowired
    private HotelUserRepository hotelUserRepository;
    @Autowired
    private RoleService roleService;

    public List<HotelUser> getAllUsers() {
        return hotelUserRepository.findAll();
    }

    public HotelUser saveUser(HotelUser user) {
        return hotelUserRepository.save(user);
    }

    @Transactional
    public void updateUser(Integer id, String firstName, String lastName, Date dob, String address, String roleName) {
        HotelUser user = hotelUserRepository.findById(id).orElseThrow();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDob(dob);
        user.setUserAddress(address);
        user.setRole(roleService.setUserRole(roleName));

        hotelUserRepository.save(user);
    }

    public Optional<HotelUser> findByEmail(String email) {
        return hotelUserRepository.findByEmail(email);
    }

    public void deleteUser(HotelUser user) {
        hotelUserRepository.delete(user);
    }

    public List<HotelUser> findByRole(String roleName) {
        return hotelUserRepository.findByRole_RoleName(roleName);
    }

    public HotelUser findById(Integer id) {
        return hotelUserRepository.findById(id).orElse(null);
    }

    public List<HotelUser> getAllEmployeesAndAdmins() {
        List<HotelUser> employees = hotelUserRepository.findByRole_RoleName("employee");
        List<HotelUser> admins = hotelUserRepository.findByRole_RoleName("admin");
        employees.addAll(admins);
        return employees;
    }

    public List<HotelUser> getAllCustomers() {
        return hotelUserRepository.findByRole_RoleName("customer");
    }

    
}
