package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataProcessingService {

    public UserRepository getRepository() {
        return null;
    }

    public List<User> sortUsersByAge(List<User> users) {
        return users;
    }
}
