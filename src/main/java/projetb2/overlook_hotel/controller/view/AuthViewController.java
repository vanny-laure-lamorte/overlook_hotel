package projetb2.overlook_hotel.controller.view;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Controller
public class AuthViewController {

    @Autowired
    private RestTemplate restTemplate;
    /**
     * Handles user registration.
     *
     * @param model Model to add attributes for the view
     * @param firstName User's first name
     * @param lastName User's last name
     * @param email User's email
     * @param password User's password
     * @return Redirects to the home page with a success or error message
     */
    @PostMapping("/login")
    public String handleLogin(Model model,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String tab) {

        try {
            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("email", email);
            requestBody.add("password", password);
            requestBody.add("employeeTab", String.valueOf("employee".equals(tab)));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

            restTemplate.postForEntity("http://localhost:8080/api/auth/login", request, String.class);

            return "redirect:/";

        } catch (HttpClientErrorException e) {
            String errorMsg = e.getResponseBodyAsString();
            System.out.println("Erreur reçue : " + errorMsg);
            String message = e.getResponseBodyAsString();
            if (message == null || message.isBlank()) {
                if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                    message = "Unauthorized access - Invalid email or password";
                } else if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                    message = "Forbidden access";
                } else {
                    message = "Unexpected error occurred";
                }
            }

            return "redirect:/?modal=true&tab=" + tab + "&loginError=" +
                    URLEncoder.encode(message, StandardCharsets.UTF_8);
        }
    }

}
