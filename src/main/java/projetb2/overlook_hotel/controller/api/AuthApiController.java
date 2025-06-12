package projetb2.overlook_hotel.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.model.Role;
import projetb2.overlook_hotel.repository.HotelUserRepository;
import projetb2.overlook_hotel.repository.RoleRepository;
import projetb2.overlook_hotel.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    @Autowired
    private HotelUserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private AuthService authService;

    /**
     * Handles user registration.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password) {

        if (userRepo.findByEmail(email).isPresent()) {
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
        user.setUserPassword(authService.encodePassword(password));
        user.setRole(customerRole);

        userRepo.save(user);
        return ResponseEntity.ok("Registration successful");
    }

    /**
     * Handles user login via API.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(defaultValue = "false") boolean employeeTab) {

        try {
            HotelUser user = authService.authenticate(email, password);

            String role = user.getRole().getRoleName();
            if (employeeTab && !(role.equalsIgnoreCase("employee") || role.equalsIgnoreCase("admin"))) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .contentType(MediaType.TEXT_PLAIN)
                        .body("Access denied. You are not an employee or admin.");
            }

            return ResponseEntity.ok("Login successful as " + capitalize(role));

        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(e.getReason()); // Utilise le message personnalisé depuis AuthService
        }
    }

    private String capitalize(String role) {
        return role.substring(0, 1).toUpperCase() + role.substring(1).toLowerCase();
    }
}
