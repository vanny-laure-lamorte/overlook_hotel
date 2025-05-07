package projetb2.overlook_hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="booking")
public class Booking {
    private int id;
    private int userId;
    private int roomId;
    private String startDate;
    private String endDate;
    private String status;
    private double totalPrice;
}
