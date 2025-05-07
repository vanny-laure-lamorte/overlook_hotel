package projetb2.overlook_hotel.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import projetb2.overlook_hotel.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    
}
