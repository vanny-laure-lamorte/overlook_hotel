package projetb2.overlook_hotel.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.repository.HotelUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner dataInitializers(HotelUserRepository userHotelRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            Iterable<HotelUser> users = userHotelRepository.findAll();

            for (HotelUser user : users) {
                if (user.getUserPassword() != null && !user.getUserPassword().isEmpty()) {
                    String encodedPassword = passwordEncoder.encode(user.getUserPassword());
                    user.setUserPassword(encodedPassword);
                    userHotelRepository.save(user);
                }
            }

            System.out.println("Mots de passe des utilisateurs encodés avec succès !");
        };
    }
}
