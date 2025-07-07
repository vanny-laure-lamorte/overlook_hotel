package projetb2.overlook_hotel.controller.view;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import projetb2.overlook_hotel.model.Booking;
import projetb2.overlook_hotel.model.Feedback;
import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.repository.BookingRepository;
import projetb2.overlook_hotel.repository.HotelUserRepository;
import projetb2.overlook_hotel.service.FeedbackService;

@Controller
public class FeedbackViewController {

    private final FeedbackService feedbackService;
    private final BookingRepository bookingRepository;
    private final HotelUserRepository userRepository;

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
    public String showFeedback(Model model) {

        Feedback feedback = new Feedback();
        model.addAttribute("hotelUserId", 1);
        model.addAttribute("bookingId", 1);
        model.addAttribute("rating", 5);

        model.addAttribute("feedback", feedback);
        model.addAttribute("fragmentPath", "fragments/feedbacks.html");
        model.addAttribute("fragmentName", "fgt-feedback");
        return "layout/connectedLayout";
    }

    @PostMapping("/feedback/submit")
    public String submitFeedback(
            @RequestParam("feedback") Feedback feedback,
            @RequestParam(value = "booking") Integer bookingId,
            @RequestParam(value = "hotelUser") Integer userId,
            Model model) {

        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        HotelUser hotelUser = userRepository.findById(userId).orElse(null);

        feedback.setBooking(booking);
        feedback.setHotelUser(hotelUser);
        feedback.setCreatedAt(LocalDateTime.now());

        System.out.println("Submitting feedback: " + feedback);

        feedbackService.saveFeedback(feedback);

        return "layout/connectedLayout";
    }
}
