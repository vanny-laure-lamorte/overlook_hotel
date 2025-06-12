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

    public HotelUser authenticate(String email, String password) {
        HotelUser user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                        "No account found with this email."));

        if (!encoder.matches(password, user.getUserPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect email or password.");
        }

        return user;
    }

    public String encodePassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }
}