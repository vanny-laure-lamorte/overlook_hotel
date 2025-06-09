package projetb2.overlook_hotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name="booking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private HotelUser user;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @Column(name = "arriving_date")
    private LocalDate arrivingDate;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "booking_status")
    private String bookingStatus;

    @Column(name = "confirmation_number")
    private int confirmationNumber;

    private int adults;
    private int children;

    private int bill;

}
