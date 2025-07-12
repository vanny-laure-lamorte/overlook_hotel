package projetb2.overlook_hotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "booking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private HotelUser user;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @Column(name = "arriving_date")
    private LocalDate arrivingDate;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status", nullable = false)
    private BookingStatus bookingStatus;

    @Column(name = "confirmation_number")
    private int confirmationNumber;

    private int adults;
    private int children;

    private int bill;

    public long getDuration() {
        return ChronoUnit.DAYS.between(arrivingDate, departureDate);
    }
}
