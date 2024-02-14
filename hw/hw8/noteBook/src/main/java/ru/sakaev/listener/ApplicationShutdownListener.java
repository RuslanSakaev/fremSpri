package ru.sakaev.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ru.sakaev.model.UserActionLog;
import ru.sakaev.repository.UserActionLogRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ApplicationShutdownListener implements ApplicationListener<ContextClosedEvent> {

    private final UserActionLogRepository userActionLogRepository;
    private static final Logger logger = LoggerFactory.getLogger(ApplicationShutdownListener.class);

    @Autowired
    public ApplicationShutdownListener(UserActionLogRepository userActionLogRepository) {
        this.userActionLogRepository = userActionLogRepository;
    }

    @Override
    public void onApplicationEvent(@NonNull ContextClosedEvent event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_action_log.txt"))) {
            List<UserActionLog> logs = userActionLogRepository.findAll();
            for (UserActionLog log : logs) {
                writer.write(log.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            logger.error("Failed to write user action log to file", e);
        }
    }
}
