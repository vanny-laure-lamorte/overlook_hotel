package projetb2.overlook_hotel.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projetb2.overlook_hotel.model.Feedback;
import projetb2.overlook_hotel.service.FeedbackService;

@RestController
@RequestMapping("/api-feedback")
public class FeedbackApiController {

    private final FeedbackService feedbackService;

    public FeedbackApiController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

     /**
     * Retrieve a single feedback by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Integer id) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        return ResponseEntity.ok(feedback);
    }

     /**
     * Submit new feedback.
     */
    @PostMapping
    public ResponseEntity<String> submitFeedback(@ModelAttribute Feedback feedback) {
        feedbackService.saveFeedback(feedback);
        return ResponseEntity.ok("Feedback submitted successfully.");
    }

    /**
     * Update feedback by ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateFeedback(@PathVariable Integer id, @RequestBody Feedback updatedFeedback) {
        feedbackService.updateFeedback(id, updatedFeedback);
        return ResponseEntity.ok("Feedback updated successfully.");
    }

    /**
     * Delete feedback by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Integer id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.ok("Feedback deleted successfully.");
    }
}
