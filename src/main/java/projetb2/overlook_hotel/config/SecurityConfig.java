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
                                "/view/search/**")
                        .permitAll()
                        .requestMatchers("/test").authenticated()
                        .anyRequest().authenticated())
                        .exceptionHandling(exception -> exception
                        .accessDeniedPage("/error/403"));

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