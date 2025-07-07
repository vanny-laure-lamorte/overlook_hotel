package projetb2.overlook_hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
<<<<<<< HEAD
                                "/", "/favicon.ico", "/css/**", "/js/**", "/images/**", "/fonts/**",
                                "/api/auth/register")
=======
                            "/favicon.ico",
                                "/api/auth/**",
                                "/login",
                                "/login/**",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/fonts/roboto/**",
                                "/",
                                "/error/**",
                                "api/admin/**",
                                "api/rooms/**",
                                "/rooms-list",
                                "/home",
                                "/feedback",
                                "/feedback/**",
                                "/api/feedback/**",
                                "/payment",
                                "/spa",
                                "/meeting-room**",
                                "/view/search/**")
>>>>>>> 7235656 (✨ <feat> add files for feedback form.)
                        .permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/auth")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .failureUrl("/auth?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}