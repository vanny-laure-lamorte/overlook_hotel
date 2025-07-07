package projetb2.overlook_hotel.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetb2.overlook_hotel.model.Feedback;
import projetb2.overlook_hotel.repository.FeedbackRepository;
@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;
    public Feedback getFeedbackById(Integer id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    public void updateFeedback(Integer id, Feedback updatedFeedback) {
        Feedback existing = feedbackRepository.findById(id).orElse(null);
        if (existing != null) {
            updatedFeedback.setId(id);
            feedbackRepository.save(updatedFeedback);
        }
    }

    public void viewFeedback() {
        System.out.println("Viewing feedback...");
        // Logic to retrieve and display feedback
    }


}
