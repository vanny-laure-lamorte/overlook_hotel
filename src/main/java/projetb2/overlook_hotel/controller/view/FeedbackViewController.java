package projetb2.overlook_hotel.controller.view;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import projetb2.overlook_hotel.model.Booking;
import projetb2.overlook_hotel.model.Feedback;
import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.repository.BookingRepository;
import projetb2.overlook_hotel.repository.HotelUserRepository;
import projetb2.overlook_hotel.service.FeedbackService;
import projetb2.overlook_hotel.service.HotelUserService;

@Controller
public class FeedbackViewController {

    private final FeedbackService feedbackService;
    private final BookingRepository bookingRepository;
    private final HotelUserRepository userRepository;

    @Autowired
    private HotelUserService hotelUserService;

    public FeedbackViewController(
            FeedbackService feedbackService,
            BookingRepository bookingRepository,
            HotelUserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.feedbackService = feedbackService;
        this.userRepository = userRepository;
    }

    /*
     * Handles the feedback page view.
     */
    @GetMapping("/feedback")
    public String showFeedback(
        @AuthenticationPrincipal UserDetails currentUser,
        Model model) {

        Feedback feedback = new Feedback();

        Optional<HotelUser> hotelUserOpt = hotelUserService.findByEmail(currentUser.getUsername());
        if (hotelUserOpt.isEmpty()) {
            return "redirect:/login";
        }
        HotelUser hotelUser =  hotelUserOpt.get();
        model.addAttribute("feedback", feedback);
        model.addAttribute("hotelUser", hotelUser);

        model.addAttribute("fragmentPath", "fragments/feedbacks.html");
        model.addAttribute("fragmentName", "fgt-feedback");
        return "layout/connectedLayout";
    }

    @PostMapping("/feedback/submit")
    public String submitFeedback(
        @ModelAttribute Feedback feedback,
        @RequestParam(value ="booking") Integer bookingId,
        @RequestParam(value ="hotelUser") Integer hotelUserId,
        Model model) {

        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        HotelUser hotelUser = userRepository.findById(hotelUserId).orElse(null);

        if (booking == null || hotelUser == null) {
            model.addAttribute("error", "Erreur : réservation ou utilisateur introuvable.");
            return "layout/connectedLayout";
        }

        feedback.setBooking(booking);
        feedback.setHotelUser(hotelUser);
        feedback.setCreatedAt(LocalDateTime.now());
        feedbackService.saveFeedback(feedback);

        model.addAttribute("success", "Feedback soumis avec succès !");
        model.addAttribute("feedback", new Feedback());
        model.addAttribute("hotelUser", hotelUser);
        model.addAttribute("fragmentPath", "fragments/feedbacks.html");
        model.addAttribute("fragmentName", "fgt-feedback");

    return "layout/connectedLayout";    }
}