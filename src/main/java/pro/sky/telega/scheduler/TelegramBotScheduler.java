package pro.sky.telega.scheduler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.scheduling.annotation.Scheduled;
import pro.sky.telega.model.NotificationTask;
import pro.sky.telega.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.util.List;

public class TelegramBotScheduler {
    private final TelegramBot telegramBot;
    private final NotificationTaskRepository repository;

    public TelegramBotScheduler(TelegramBot telegramBot, NotificationTaskRepository repository) {
        this.telegramBot = telegramBot;
        this.repository = repository;
    }

    @Scheduled(fixedDelay = 5_000L)
    public void sendTasksByCurrentMinute() {
        LocalDateTime currentMinute = LocalDateTime.now();
        List<NotificationTask> notificationTasks = repository.findAllByNotificationTimeLessThan(currentMinute);
        notificationTasks.forEach(notificationTask -> {
            telegramBot.execute(new SendMessage(notificationTask.getChatId(), notificationTask.getNotificationText()));
        });
    }
}
