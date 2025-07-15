package projetb2.overlook_hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Constructor for HotelUserService.
     *
     * @param hotelUserRepository the HotelUserRepository to be used by this service
     * @param roleService the RoleService to be used by this service
     */
    public List<HotelUser> getAllUsers() {
        return hotelUserRepository.findAll();
    }

    /**
     * Saves a HotelUser to the repository.
     *
     * @param user the HotelUser to save
     * @return the saved HotelUser
     */
    public HotelUser saveUser(HotelUser user) {
        return hotelUserRepository.save(user);
    }

    /**
     * Updates the details of a HotelUser.
     *
     * @param id the ID of the user to update
     * @param firstName the new first name
     * @param lastName the new last name
     * @param dob the new date of birth
     * @param address the new address
     * @param roleName the new role name
     */
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

    /**
     * Finds a HotelUser by their email address.
     *
     * @param email the email of the user to find
     * @return an Optional containing the HotelUser if found, or empty if not found
     */
    public Optional<HotelUser> findByEmail(String email) {
        return hotelUserRepository.findByEmail(email);
    }

    /**
     * Deletes a HotelUser from the repository.
     *
     * @param user the HotelUser to delete
     */
    public void deleteUser(HotelUser user) {
        hotelUserRepository.delete(user);
    }

    /**
     * Finds a HotelUser by their ID.
     *
     * @param id the ID of the user to find
     * @return the HotelUser if found, or null if not found
     */
    public List<HotelUser> findByRole(String roleName) {
        return hotelUserRepository.findByRole_RoleName(roleName);
    }

    /**
     * Finds a HotelUser by their ID.
     *
     * @param id the ID of the user to find
     * @return the HotelUser if found, or null if not found
     */
    public HotelUser findById(Integer id) {
        return hotelUserRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves all employees and admins from the repository.
     *
     * @return a list of HotelUser objects representing employees and admins
     */
    public List<HotelUser> getAllEmployeesAndAdmins() {
        List<HotelUser> employees = hotelUserRepository.findByRole_RoleName("employee");
        List<HotelUser> admins = hotelUserRepository.findByRole_RoleName("admin");
        employees.addAll(admins);
        return employees;
    }

    /**
     * Retrieves all customers from the repository.
     *
     * @return a list of HotelUser objects representing customers
     */
    public List<HotelUser> getAllCustomers() {
        return hotelUserRepository.findByRole_RoleName("customer");
    }

    /**
     * Adds a new HotelUser with the specified details.
     *
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param email the email of the user
     * @param rawPassword the raw password of the user
     * @return the newly created HotelUser
     */
    @Transactional
    public HotelUser addUser(String firstName,
            String lastName,
            String email,
            String rawPassword) {

        if (hotelUserRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already used");
        }

        HotelUser user = new HotelUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setUserPassword(passwordEncoder.encode(rawPassword));
        user.setRole(roleService.setUserRole("customer"));

        return hotelUserRepository.save(user);
    }

    /**
     * Calculates the profile completion percentage for a HotelUser.
     *
     * @param user the HotelUser to check
     * @return the percentage of filled fields
     */
    public int findProfileCompletionHotelUser(HotelUser user) {
        int filled = 0;
        int totalFields = 6;

        if (user.getFirstName() != null && !user.getFirstName().isEmpty())
            filled++;
        if (user.getLastName() != null && !user.getLastName().isEmpty())
            filled++;
        if (user.getDob() != null && !user.getDob().toString().isEmpty())
            filled++;
        if (user.getUserAddress() != null && !user.getUserAddress().isEmpty())
            filled++;
        if (user.getPhoneNumber() != null && !user.getPhoneNumber().isEmpty())
            filled++;
        if (user.getEmail() != null && !user.getEmail().isEmpty())
            filled++;

        return (int) Math.round((filled * 100.0) / totalFields);
    }
}