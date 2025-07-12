package projetb2.overlook_hotel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import projetb2.overlook_hotel.dto.BookingDTO;
import projetb2.overlook_hotel.model.HotelUser;

@Service
public class AdminService {

    private final BookingService bookingService;
    private final RoomService roomService;
    private final HotelUserService userService;

    public AdminService(BookingService bookingService, RoomService roomService, HotelUserService userService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
        this.userService = userService;
    }

    public List<HotelUser> getAllEmployees() {
        return userService.getAllEmployeesAndAdmins();
    }

    /**
     * Retrieves a list of all customers.
     *
     * @return List of HotelUser objects representing all customers
     */
    public List<HotelUser> getAllCustomers() {
        return userService.getAllCustomers();
    }

    /**
     * Retrieves a list of all booking information.
     *
     * @return List of BookingDTO objects containing booking details
     */
    public List<BookingDTO> getAllBookingInfos() {
        return bookingService.getAllBookings().stream()
                .map(booking -> new BookingDTO(
                        booking.getId(),
                        booking.getUser().getFirstName() + " " + booking.getUser().getLastName(),
                        booking.getRoom().getId(),
                        booking.getRoom().getRoomTitle(),
                        booking.getArrivingDate(),
                        booking.getDepartureDate(),
                        booking.getBookingStatus()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of all rooms.
     *
     * @return List of Room objects representing all rooms
     */
    public List<projetb2.overlook_hotel.model.Room> getAllRooms() {
        return roomService.getAllRooms();
    }
}
