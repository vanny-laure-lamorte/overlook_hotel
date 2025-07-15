package projetb2.overlook_hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetb2.overlook_hotel.repository.UserNotificationRepository;

@Service
public class UserNotificationService {
    @Autowired
    private UserNotificationRepository notificationRepository;

    public void sendNotification(String message) {
        System.out.println("Notification sent: " + message);
    }
}
