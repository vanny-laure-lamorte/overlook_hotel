package projetb2.overlook_hotel.model;

import java.util.Arrays;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "accommodation_type")
    private String accommodationType;

    private double price;
    private int capacity;

    @Column(name = "room_description")
    private String roomDescription;

    @Column(name = "room_title")
    private String roomTitle;

    @Column(name = "room_image")
    private String roomImage;

    @Column(name = "amenities")
    private String amenitiesList;

    @Transient
    private List<String> amenitiesWords;

    /**
     * Splits the amenitiesList (a comma-separated string) into a clean list of individual amenities.
     * @return a List<String> containing trimmed, non-empty amenity names.
     */
    public List<String> getAmenitiesWords() {
        if (amenitiesList == null) return List.of();
        return Arrays.stream(amenitiesList.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toList();
    }
}
