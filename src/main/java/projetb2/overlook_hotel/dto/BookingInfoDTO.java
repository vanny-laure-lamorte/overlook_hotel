package projetb2.overlook_hotel.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingInfoDTO {
    private Integer bookingId;
    private String username;
    private Integer roomId;
    private String roomTitle;
    private LocalDate arrivingDate;
    private LocalDate departureDate;
    private String bookingStatus;

    public String getFormattedArrivingDate() {
        return arrivingDate != null ? arrivingDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
    }

    public String getFormattedDepartureDate() {
        return departureDate != null ? departureDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
    }
}
