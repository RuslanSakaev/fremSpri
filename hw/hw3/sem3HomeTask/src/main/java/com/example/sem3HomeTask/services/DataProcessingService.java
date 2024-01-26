package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataProcessingService {

    private final UserRepository userRepository;

    @Autowired
    public DataProcessingService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getRepository() {
        return userRepository;
    }

    public List<User> sortUsersByAge(List<User> users) {
        return userRepository.getUsers().stream()
                .sorted(Comparator.comparingInt(User::getAge))
                .collect(Collectors.toList());
    }

    public List<User> filterUsersByAge(int age) {
        return userRepository.getUsers().stream()
                .filter(user -> user.getAge() == age)
                .collect(Collectors.toList());
    }

    public double calculateAverageAge() {
        List<User> users = userRepository.getUsers();
        return users.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0.0); // Возвращает 0.0, если список пользователей пуст

    }
}

