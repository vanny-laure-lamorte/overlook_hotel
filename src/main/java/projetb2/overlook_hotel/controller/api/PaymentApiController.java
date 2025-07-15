package projetb2.overlook_hotel.controller.api;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import java.util.Map;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import projetb2.overlook_hotel.config.StripeConfig;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/checkout")
public class PaymentApiController {

    private final StripeConfig stripeConfig;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeConfig.getSecretKey();
    }

    @PostMapping("/create-checkout-session")
    @ResponseBody
    public Map<String, String> createCheckoutSession(
            @RequestParam double amount,
            @RequestParam int roomId,
            @RequestParam int userId,
            @RequestParam int AdultsCount,
            @RequestParam int ChildrenCount,
            @RequestParam String arrivalDate,
            @RequestParam String departureDate) throws StripeException {

        long amountInCents = Math.round(amount * 100);

        String baseUrl = "http://localhost:8080/view/booking/summary";
        String params = String.format(
                "?payment=success&roomId=%d&userId=%d&AdultsCount=%d&ChildrenCount=%d&arrivalDate=%s&departureDate=%s",
                roomId, userId, AdultsCount, ChildrenCount, arrivalDate, departureDate);
        String cancelParams = params.replace("payment=success", "payment=cancel");

        SessionCreateParams sessionParams = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(baseUrl + params)
                .setCancelUrl(baseUrl + cancelParams)
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("eur")
                                                .setUnitAmount(amountInCents)
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName("Booking Payment")
                                                                .build())
                                                .build())
                                .build())
                .build();

        Session session = Session.create(sessionParams);
        return Map.of("id", session.getId());
    }
}
