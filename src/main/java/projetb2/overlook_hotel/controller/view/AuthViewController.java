// --- AuthViewController.java ---
package projetb2.overlook_hotel.controller.view;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import projetb2.overlook_hotel.service.CustomUserDetailsService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class AuthViewController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    public String handleLogin(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(required = false) String tab,
            HttpSession session) {
        try {
            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("email", email);
            body.add("password", password);
            body.add("employeeTab", String.valueOf("employee".equalsIgnoreCase(tab)));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    "http://localhost:8080/api/auth/login",
                    request,
                    String.class);

            // Spring Security login session
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);

            return "redirect:/";

        } catch (RestClientResponseException e) {
            String msg;
            switch (e.getRawStatusCode()) {
                case 401 -> msg = "Invalid email or password";
                case 403 -> msg = "Access denied";
                default -> msg = "Login failed";
            }
            String safeTab = (tab == null || tab.isBlank()) ? "customer" : tab;
            return "redirect:/?modal=true&tab=" + safeTab + "&loginError=" +
                    URLEncoder.encode(msg, StandardCharsets.UTF_8);
        }
    }

    @GetMapping("/logout")
    public String handleLogout(HttpSession session) {
        session.invalidate();
        SecurityContextHolder.clearContext();
        return "redirect:/";
    }
}