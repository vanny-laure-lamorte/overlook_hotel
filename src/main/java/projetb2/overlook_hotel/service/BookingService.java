package projetb2.overlook_hotel.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projetb2.overlook_hotel.model.Booking;
import projetb2.overlook_hotel.model.BookingStatus;
import projetb2.overlook_hotel.model.Room;
import projetb2.overlook_hotel.repository.BookingRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepo;
    private final RoomService roomService;

    /**
     * Constructor for BookingService.
     *
     * @param bookingRepo the BookingRepository to be used by this service
     * @param roomService the RoomService to be used by this service
     */
    public BookingService(BookingRepository bookingRepo, RoomService roomService) {
        this.bookingRepo = bookingRepo;
        this.roomService = roomService;
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    /*
     * Accepts a booking by its ID.
     * If the booking is successfully accepted, it returns true.
     */
    @Transactional
    public boolean acceptBooking(Integer bookingId) {
        return bookingRepo.findById(bookingId).map(booking -> {
            if (booking.getBookingStatus() != BookingStatus.PENDING)
                return false;
            booking.setBookingStatus(BookingStatus.ACCEPTED);
            bookingRepo.save(booking);
            return true;
        }).orElse(false);
    }

    /*
     * Declines a booking by its ID.
     * If the booking is successfully declined, it returns true.
     */
    @Transactional
    public boolean declineBooking(Integer bookingId) {
        return bookingRepo.findById(bookingId).map(booking -> {
            if (booking.getBookingStatus() != BookingStatus.PENDING)
                return false;
            booking.setBookingStatus(BookingStatus.DECLINED);
            bookingRepo.save(booking);
            return true;
        }).orElse(false);
    }

    /*
     * Updates a booking with the provided details.
     * If the booking is successfully updated, it returns true.
     */
    @Transactional
    public boolean updateBooking(
            Integer bookingId,
            Integer roomId,
            LocalDate arrival,
            LocalDate departure,
            BookingStatus status) {
        return bookingRepo.findById(bookingId).map(booking -> {
            Room room = roomService.getRoomById(roomId);
            if (room == null)
                return false;
            room.setRoomTitleLabel(roomService.mapRoomTitle(room.getRoomTitle()));

            booking.setRoom(room);
            booking.setArrivingDate(arrival);
            booking.setDepartureDate(departure);
            booking.setBookingStatus(status);
            bookingRepo.save(booking);
            return true;
        }).orElse(false);
    }

    public List<Booking> getPastBookingsForCurrentUser(Integer currentUserId) {
        LocalDate today = LocalDate.now();
        List<Booking> bookings = bookingRepo.findPastBookingsByUserId(currentUserId, today);
        for (Booking booking : bookings) {
            Room room = booking.getRoom();
            if (room != null) {
                room.setRoomTitleLabel(roomService.mapRoomTitle(room.getRoomTitle()));
            }
        }
        return bookings;
    }

     /**
     * Cancel all bookings associated with a user by their user ID.
     *
     * @param userId the ID of the user whose bookings are to be deleted
     */
    @Transactional
    public void cancelBookingsByUserId(Integer userId) {
        List<Booking> bookings = bookingRepo.findByUser_Id(userId);
        for (Booking booking : bookings) {
            System.out.println("\n\n\nCancelling booking with ID: " + booking.getId() + "\n\n\n");
            booking.setBookingStatus(BookingStatus.CANCELLED);
            booking.setUser(null);
            bookingRepo.save(booking);
        }
    }
}

