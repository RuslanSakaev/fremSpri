package ru.sakaev.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "user_action_log")
public class UserActionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String methodName;
    private String className;
    private String arguments;
    private LocalDateTime timestamp;

    public UserActionLog() {
        this.timestamp = LocalDateTime.now();
    }

    public UserActionLog(String methodName, String className, String arguments) {
        this.methodName = methodName;
        this.className = className;
        this.arguments = arguments;
        this.timestamp = LocalDateTime.now();
    }
}
