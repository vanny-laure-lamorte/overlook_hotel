package projetb2.overlook_hotel.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetb2.overlook_hotel.repository.FeedbackRepository;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public void submitFeedback(String feedback) {
        System.out.println("Feedback submitted: " + feedback);
    }

    public void deleteFeedback(int feedbackId) {
        System.out.println("Feedback with ID " + feedbackId + " deleted.");
        // Logic to delete feedback by ID
    }

    public void updateFeedback(int feedbackId, String newFeedback) {
        System.out.println("Feedback with ID " + feedbackId + " updated to: " + newFeedback);
        // Logic to update feedback by ID
    }

    public void viewFeedback() {
        System.out.println("Viewing feedback...");
        // Logic to retrieve and display feedback
    }


}
