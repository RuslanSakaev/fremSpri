package com.example.AdminPanelV1.services.tasks;

import com.example.AdminPanelV1.domain.User;

import java.util.List;

public interface TaskService {
    List<User> sortUsersByAge(List<User> users);

    List<User> filterUsersByAge(List<User> users, int age);

    String calculateAverageAge(List<User> users);
}
