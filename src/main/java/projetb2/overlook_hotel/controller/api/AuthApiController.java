package projetb2.overlook_hotel.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import projetb2.overlook_hotel.model.Role;
import projetb2.overlook_hotel.model.UserHotel;
import projetb2.overlook_hotel.repository.RoleRepository;
import projetb2.overlook_hotel.repository.UserHotelRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    @Autowired
    private UserHotelRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password) {

        System.out.println("-----> Register request: " + firstName + " " + lastName + " | " + email);

        if (userRepo.findByEmail(email).isPresent()) {
            System.out.println("-----> Email already exists: " + email);
            return ResponseEntity.badRequest().body("Email already exists");
        }

        Role customerRole = roleRepo.findByRoleName("customer").orElseThrow();
        System.out.println("-----> Role for customer: " + customerRole.getRoleName());

        UserHotel user = new UserHotel();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setUserPassword(encoder.encode(password));
        user.setRole(customerRole);

        userRepo.save(user);
        System.out.println("-----> User registered successfully: " + user.getEmail());
        return ResponseEntity.ok("Registration successful");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(defaultValue = "false") boolean employeeTab) {

                System.out.println("Encoded : "+encoder.encode("password123"));
        System.out.println("-----> Login request: " + email + " | employeeTab: " + employeeTab);

        Optional<UserHotel> userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) {
            System.out.println("-----> No account found with this email: " + email);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No account found with this email");
        }

        UserHotel user = userOpt.get();
        System.out.println("-----> User found: " + user.getEmail());

        System.out.println("-----> password: " + password + " | user.password: " + user.getUserPassword());
        System.out.println("-----> password: " + encoder.encode("password123") + " | user.password: " + user.getUserPassword());
        if (!encoder.matches(password, user.getUserPassword())) {
            System.out.println("-----> Invalid credentials for user: " + email);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        String role = user.getRole().getRoleName();
        System.out.println("-----> User role: " + role);

        if (employeeTab) {
            if (!(role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("employee"))) {
                System.out.println("-----> Access denied for non-employee/admin: " + role);
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("Access denied: Not an employee or admin");
            }
        }

        if (role.equalsIgnoreCase("customer")) {
            return ResponseEntity.ok("Login successful as Customer");
        }

        if (role.equalsIgnoreCase("employee")) {
            return ResponseEntity.ok("Login successful as Employee");
        }

        if (role.equalsIgnoreCase("admin")) {
            return ResponseEntity.ok("Login successful as Admin");
        }

        System.out.println("-----> Access denied: Unknown role for user: " + email);
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Access denied: Unknown role");
    }
}
