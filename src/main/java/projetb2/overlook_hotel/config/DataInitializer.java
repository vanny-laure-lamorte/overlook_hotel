package projetb2.overlook_hotel.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import projetb2.overlook_hotel.model.UserHotel;
import projetb2.overlook_hotel.repository.UserHotelRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner dataInitializers(UserHotelRepository userHotelRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            Iterable<UserHotel> users = userHotelRepository.findAll();

            for (UserHotel user : users) {
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
