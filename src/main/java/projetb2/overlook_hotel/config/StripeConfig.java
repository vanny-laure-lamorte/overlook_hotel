package projetb2.overlook_hotel.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "stripe")
public class StripeConfig {
    private String secretKey;
    private String publicKey;
}
