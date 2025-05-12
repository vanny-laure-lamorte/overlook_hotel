package projetb2.overlook_hotel.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Controller
public class AuthViewController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/login")
    public String defaultTab(Model model) {
        model.addAttribute("selectedTab", "customer");
        model.addAttribute("fragmentPath", "fragments/connexion-modal.html");
        model.addAttribute("fragmentName", "connexion-modal");
        return "layout/connectedLayout";
    }

    @PostMapping("/login")
    public String handleLoginOrTabChange(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password,
            @RequestParam("tab") String tab,
            Model model) {

        model.addAttribute("selectedTab", tab);
        model.addAttribute("fragmentPath", "fragments/connexion-modal.html");
        model.addAttribute("fragmentName", "connexion-modal");

        if (email == null || password == null) {
            return "layout/connectedLayout";
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("email", email);
            params.add("password", password);
            params.add("employeeTab", String.valueOf(tab.equals("employee")));

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    "http://localhost:8080/api/auth/login",
                    request,
                    String.class);
        model.addAttribute("fragmentPath", "fragments/test.html");
        model.addAttribute("fragmentName", "test");            return "layout/connectedLayout";

        } catch (HttpClientErrorException e) {
            model.addAttribute("loginError", e.getResponseBodyAsString());
            return "layout/connectedLayout";
        }
    }
}
