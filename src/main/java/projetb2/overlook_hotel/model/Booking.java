package projetb2.overlook_hotel.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "room_id")
    private int roomId;

    @Column(name = "arriving_date")
    private LocalDate arrivingDate;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "booking_status")
    private String bookingStatus;

    @Column(name = "confirmation_number")
    private int confirmationNumber;

    private int guests;

    @Column(name = "bill")
    private int bill;
}
