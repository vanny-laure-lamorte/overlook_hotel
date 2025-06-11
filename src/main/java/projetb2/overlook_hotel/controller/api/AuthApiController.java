package projetb2.overlook_hotel.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import projetb2.overlook_hotel.model.Role;
import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.repository.RoleRepository;
import projetb2.overlook_hotel.repository.HotelUserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    @Autowired
    private HotelUserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder encoder;

    /**
     * Handles user registration.
     *
     * @param firstName User's first name
     * @param lastName User's last name
     * @param email User's email
     * @param password User's password
     * @return ResponseEntity with registration status
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password) {

        System.out.println("-----> Register request: " + firstName + " " + lastName + " | " + email);

        if (userRepo.findByEmail(email).isPresent()) {
            System.out.println("-----> Email already exists: " + email);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("An account already exists with this email.");
        }

        Role customerRole = roleRepo.findByRoleName("customer")
                .orElseThrow(() -> new RuntimeException("Customer role not found"));

        HotelUser user = new HotelUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setUserPassword(encoder.encode(password));
        user.setRole(customerRole);

        userRepo.save(user);
        System.out.println("-----> User registered successfully: " + user.getEmail());

        return ResponseEntity.ok("Registration successful");
    }
    /**
     * Handles user login.
     *
     * @param email User's email
     * @param password User's password
     * @param employeeTab Whether the request is for employee access
     * @return ResponseEntity with login status
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(defaultValue = "false") boolean employeeTab) {

        System.out.println("-----> Login request: " + email + " | employeeTab: " + employeeTab);

        Optional<HotelUser> userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) {
            System.out.println("-----> No account found with this email: " + email);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("No account found with this email.");
        }

        HotelUser user = userOpt.get();
        if (!encoder.matches(password, user.getUserPassword())) {
            System.out.println("-----> Invalid credentials for user: " + email);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("Incorrect email or password.");
        }

        String role = user.getRole().getRoleName();
        System.out.println("-----> Authenticated role: " + role);

        if (employeeTab && !(role.equalsIgnoreCase("employee") || role.equalsIgnoreCase("admin"))) {
            System.out.println("-----> Access denied for role: " + role);
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("Access denied. You are not an employee or admin.");
        }

        return ResponseEntity.ok("Login successful as " + role.substring(0, 1).toUpperCase() + role.substring(1));
    }
}
