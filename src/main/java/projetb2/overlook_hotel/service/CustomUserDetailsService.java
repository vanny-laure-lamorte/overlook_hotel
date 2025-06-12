// --- CustomUserDetailsService.java ---
package projetb2.overlook_hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.repository.HotelUserRepository;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private HotelUserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        HotelUser user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getUserPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRoleName().toUpperCase()))
        );
    }
}