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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.RedirectView;

import projetb2.overlook_hotel.model.Booking;
import projetb2.overlook_hotel.model.Feedback;
import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.repository.BookingRepository;
import projetb2.overlook_hotel.repository.HotelUserRepository;
import projetb2.overlook_hotel.service.FeedbackService;
import projetb2.overlook_hotel.service.HotelUserService;

@RequestMapping("/view/feedback")
@Controller
public class FeedbackViewController {

    private final FeedbackService feedbackService;
    private final BookingRepository bookingRepository;
    private final HotelUserRepository userRepository;
    private final HotelUserService hotelUserService;

    @Autowired
    public FeedbackViewController(
            FeedbackService feedbackService,
            BookingRepository bookingRepository,
            HotelUserRepository userRepository,
            HotelUserService hotelUserService) {
        this.feedbackService = feedbackService;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.hotelUserService = hotelUserService;
    }

    /**
     * Display the feedback form for a specific booking.
     */
    @GetMapping("")
    public String showFeedbackForm(@RequestParam("bookingId") Integer bookingId,
                                   @AuthenticationPrincipal UserDetails currentUser,
                                   Model model) {
        Optional<HotelUser> hotelUserOpt = hotelUserService.findByEmail(currentUser.getUsername());
        if (hotelUserOpt.isEmpty()) {
            return "redirect:/login";
        }

        HotelUser hotelUser = hotelUserOpt.get();
        Booking booking = bookingRepository.findById(bookingId).orElse(null);

        if (booking == null || !booking.getUser().getId().equals(hotelUser.getId())) {
            model.addAttribute("error", "Réservation invalide ou non autorisée.");
            return "layout/connectedLayout";
        }

        Feedback feedback = new Feedback();
        feedback.setBooking(booking);
        feedback.setHotelUser(hotelUser);

        model.addAttribute("feedback", feedback);
        model.addAttribute("hotelUser", hotelUser);
        model.addAttribute("booking", booking);
        model.addAttribute("fragmentPath", "fragments/feedback-form.html");
        model.addAttribute("fragmentName", "fgt-feedback-form");
        return "layout/connectedLayout";
    }

    /**
     * Display the feedback form for a specific booking.
     */
    @GetMapping("/reviews")
    public String showAllReviews(
        @AuthenticationPrincipal UserDetails currentUser,
        Model model) {
        Optional<HotelUser> hotelUserOpt = hotelUserService.findByEmail(currentUser.getUsername());
        if (hotelUserOpt.isEmpty()) {
            return "redirect:/login";
        }
        // model.addAttribute("hotelUser",hotelUser);

        model.addAttribute("fragmentPath", "fragments/feedback-reviews.html");
        model.addAttribute("fragmentName", "fgt-feedback-reviews");
        return "layout/connectedLayout";
    }


    /**
     * Submits the feedback for a booking.
     */
    @PostMapping("/submit")
    public RedirectView submitFeedback(
        @ModelAttribute Feedback feedback,
        BindingResult result,
        Model model) {

        if (feedback.getBooking() == null || feedback.getHotelUser() == null) {
            model.addAttribute("error", "Feedback invalide : réservation ou utilisateur manquant.");
            return new RedirectView("/view/booking/past-booking");
        }

        Booking booking = bookingRepository.findById(feedback.getBooking().getId()).orElse(null);
        HotelUser hotelUser = userRepository.findById(feedback.getHotelUser().getId()).orElse(null);

        if (booking == null || hotelUser == null) {
            model.addAttribute("error", "Réservation ou utilisateur non trouvé.");
            return new RedirectView("/view/booking/past-booking");
        }

        feedback.setBooking(booking);
        feedback.setHotelUser(hotelUser);
        feedback.setCreatedAt(LocalDateTime.now());
        feedbackService.saveFeedback(feedback);

        model.addAttribute("success", "Feedback soumis avec succès !");
        model.addAttribute("feedback", new Feedback());
        model.addAttribute("hotelUser", hotelUser);
        model.addAttribute("fragmentPath", "fragments/feedback-form.html");
        model.addAttribute("fragmentName", "fgt-feedback-form");
        return new RedirectView("/view/booking/past-booking");
    }
}