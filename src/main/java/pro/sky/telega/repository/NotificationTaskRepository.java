package pro.sky.telega.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telega.model.NotificationTask;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationTaskRepository extends JpaRepository<NotificationTask, Long> {
    List<NotificationTask> findAllByNotificationTimeLessThan(LocalDateTime notificationTime);
}