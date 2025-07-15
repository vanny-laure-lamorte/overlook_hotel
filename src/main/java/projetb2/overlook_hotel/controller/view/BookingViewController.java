package projetb2.overlook_hotel.controller.view;

import java.util.Optional;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import projetb2.overlook_hotel.config.StripeConfig;
import projetb2.overlook_hotel.model.Booking;
import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.service.HotelUserService;
import projetb2.overlook_hotel.service.RoomService;
import projetb2.overlook_hotel.service.BookingService;

@Controller
@RequestMapping("/view/booking")
@SessionAttributes({ "adultsCount", "childrenCount", "fragmentPath", "fragmentName" })
public class BookingViewController {

    @Autowired
    private StripeConfig stripeConfig;
    private final BookingService bookingService;
    private final HotelUserService hotelUserService;
    private final RoomService roomService;

    public BookingViewController(
            BookingService bookingService,
            HotelUserService hotelUserService,
            RoomService roomService) {
        this.bookingService = bookingService;
        this.hotelUserService = hotelUserService;
        this.roomService = roomService;
    }

    @GetMapping("/process")
    public String processSearch(
            @RequestParam int adultCount,
            @RequestParam int childCount,
            Model model) {
        model.addAttribute("adultsCount", adultCount);
        model.addAttribute("childrenCount", childCount);
        return "layout/connectedLayout";
    }

    @GetMapping("/past-booking")
    public String showPastBooking(
            Model model,
            @AuthenticationPrincipal UserDetails currentUser) {
        Optional<HotelUser> userOpt = hotelUserService.findByEmail(currentUser.getUsername());
        model.addAttribute("fragmentPath", "fragments/past-booking");
        model.addAttribute("fragmentName", "fgt-past-booking");

        if (userOpt.isPresent()) {
            HotelUser user = userOpt.get();
            model.addAttribute("user", user);
            model.addAttribute("hotelUser", user);

            List<Booking> pastBookings = bookingService.getPastBookingsForCurrentUser(user.getId());
            model.addAttribute("pastBookings", pastBookings);
        } else {
            model.addAttribute("pastBookings", List.of());
        }
        return "layout/connectedLayout";
    }

    @GetMapping("/summary")
    public String showBookingSummary(
            @RequestParam("roomId") Integer roomId,
            @RequestParam(value = "userId", required = false, defaultValue = "-1") Integer userId,
            @RequestParam("AdultsCount") Integer adultsCount,
            @RequestParam("ChildrenCount") Integer childrenCount,
            @RequestParam(value = "arrivalDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate arrivalDate,
            @RequestParam(value = "departureDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
            @RequestParam(value = "payment", required = false) String paymentStatus,
            Model model) {

        if (arrivalDate == null) {
            arrivalDate = LocalDate.now();
        }
        if (departureDate == null) {
            departureDate = arrivalDate.plusDays(1);
        }

        model.addAttribute("arrivalDate", arrivalDate);
        model.addAttribute("departureDate", departureDate);
        model.addAttribute("roomName", roomService.mapRoomTitle(roomId));
        model.addAttribute("room", roomService.getRoomById(roomId));
        model.addAttribute("userId", userId);
        model.addAttribute("adultsCount", adultsCount);
        model.addAttribute("childrenCount", childrenCount);
        model.addAttribute("fragmentPath", "fragments/booking-summary");
        model.addAttribute("fragmentName", "fgt-booking-summary");
        model.addAttribute("stripePublicKey", stripeConfig.getPublicKey());

        if ("success".equals(paymentStatus)) {
            model.addAttribute("paymentMessage", "success");
        } else if ("cancel".equals(paymentStatus)) {
            model.addAttribute("paymentMessage", "cancel");
        }

        return "layout/connectedLayout";
    }

}
