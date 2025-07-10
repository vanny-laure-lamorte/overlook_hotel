package projetb2.overlook_hotel.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Data;

import projetb2.overlook_hotel.model.BookingStatus;

@Data
@AllArgsConstructor
public class BookingInfoDTO {
    private Integer bookingId;
    private String username;
    private Integer roomId;
    private String roomTitle;
    private LocalDate arrivingDate;
    private LocalDate departureDate;
    private BookingStatus bookingStatus;

    /*
     * Returns the formatted arriving date as a string in the format "dd/MM/yyyy".
     * If the arriving date is null, it returns an empty string.
     */
    public String getFormattedArrivingDate() {
        return arrivingDate != null ? arrivingDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
    }

    /*
     * Returns the formatted departure date as a string in the format "dd/MM/yyyy".
     * If the departure date is null, it returns an empty string.
     */
    public String getFormattedDepartureDate() {
        return departureDate != null ? departureDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
    }
}
