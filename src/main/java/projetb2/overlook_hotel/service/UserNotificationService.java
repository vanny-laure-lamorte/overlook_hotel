package projetb2.overlook_hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetb2.overlook_hotel.repository.UserNotificationRepository;

@Service
public class UserNotificationService {
    @Autowired
    private UserNotificationRepository notificationRepository;

    // Example method to send a notification
    public void sendNotification(String message) {
        System.out.println("Notification sent: " + message);
    }

    // Example method to delete a notification
    public void deleteNotification(int notificationId) {
        System.out.println("Notification with ID " + notificationId + " deleted.");
    }

    // Example method to view notifications
    public void viewNotifications() {
        System.out.println("Viewing notifications...");
    }
}
