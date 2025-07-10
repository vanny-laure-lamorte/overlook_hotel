package projetb2.overlook_hotel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetb2.overlook_hotel.dto.BookingInfoDTO;
import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.repository.HotelUserRepository;

@Service
public class AdminService {

    @Autowired
    private HotelUserRepository userRepo;
    private final BookingService bookingService;
    private final RoomService roomService;

    public AdminService(BookingService bookingService, RoomService roomService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
    }

    /**
     * Retrieves a list of all employees, including both regular employees and admins.
     *
     * @return List of HotelUser objects representing all employees
     */
    public List<HotelUser> getAllEmployees() {
        List<HotelUser> employees = userRepo.findByRole_RoleName("employee");
        List<HotelUser> admins = userRepo.findByRole_RoleName("admin");

        employees.addAll(admins);
        return employees;
    }

    /**
     * Retrieves a list of all customers.
     *
     * @return List of HotelUser objects representing all customers
     */
    public List<HotelUser> getAllCustomers() {
        return userRepo.findByRole_RoleName("customer");
    }

    /**
     * Retrieves a list of all booking information.
     *
     * @return List of BookingInfoDTO objects containing booking details
     */
    public List<BookingInfoDTO> getAllBookingInfos() {
        return bookingService.getAllBookings().stream()
            .map(booking -> new BookingInfoDTO(
                booking.getId(),
                booking.getUser().getFirstName() + " " + booking.getUser().getLastName(),
                booking.getRoom().getId(),
                booking.getRoom().getRoomTitle(),
                booking.getArrivingDate(),
                booking.getDepartureDate(),
                booking.getBookingStatus()
            ))
            .collect(Collectors.toList());
    }

    public List<projetb2.overlook_hotel.model.Room> getAllRooms() {
        return roomService.getAllRooms();
    }
}
