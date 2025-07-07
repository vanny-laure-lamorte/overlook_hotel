package projetb2.overlook_hotel.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
// import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
// @NoArgsConstructor
@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Feedback() {}

    public Feedback(HotelUser hotelUser, Booking booking, Boolean travelForWork, String travelCompanions, String expectationMet, int rating, String likes, String dislikes, String summary, String response) {
        this.hotelUser = hotelUser;
        this.booking = booking;
        this.travelForWork = travelForWork;
        this.travelCompanions = travelCompanions;
        this.expectationMet = expectationMet;
        this.rating = rating;
        this.likes = likes;
        this.dislikes = dislikes;
        this.summary = summary;
        this.response = response;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", hotelUser=" + hotelUser +
                ", booking=" + booking +
                ", travelForWork=" + travelForWork +
                ", travelCompanions='" + travelCompanions + '\'' +
                ", expectationMet='" + expectationMet + '\'' +
                ", rating=" + rating +
                ", likes='" + likes + '\'' +
                ", dislikes='" + dislikes + '\'' +
                ", summary='" + summary + '\'' +
                ", response='" + response + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @JoinColumn(name = "customer_id")
    private HotelUser hotelUser;

    @ManyToOne
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private Booking booking;

    @Column(name = "travel_for_work")
    private Boolean travelForWork;

    @Column(name = "travel_companions")
    private String travelCompanions;

    @Column(name = "expectation_met")
    private String expectationMet;

    @Min(1)
    @Max(5)
    private int rating;

    private String likes;

    private String dislikes;

    private String summary;

    private String response;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}