// --- AuthService.java ---
package projetb2.overlook_hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.repository.HotelUserRepository;

@Service
public class AuthService {

    @Autowired
    private HotelUserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    /**
     * Authenticates a user by checking their email and password.
     *
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return The authenticated HotelUser object.
     * @throws ResponseStatusException if the email is not found or the password is incorrect.
     */
    public HotelUser authenticate(String email, String password) {
        HotelUser user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                        "No account found with this email."));

        if (!encoder.matches(password, user.getUserPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect email or password.");
        }
        return user;
    }

    /**
     * Encodes a raw password using the configured PasswordEncoder.
     *
     * @param rawPassword The raw password to encode.
     * @return The encoded password.
     */
    public String encodePassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }
}