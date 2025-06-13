// --- CustomUserDetailsService.java ---
package projetb2.overlook_hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.repository.HotelUserRepository;
import projetb2.overlook_hotel.security.CustomUserDetails;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private HotelUserRepository userRepo;

    /**
     * Loads a user by their email address.
     *
     * @param email the email of the user to load
     * @return UserDetails containing user information and authorities
     * @throws UsernameNotFoundException if no user is found with the given email
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Attempting to load user by email: " + email);
        HotelUser user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        System.out.println("User found: " + user.getEmail());

        return new CustomUserDetails(user, List.of(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().getRoleName().toUpperCase())));
    }
}