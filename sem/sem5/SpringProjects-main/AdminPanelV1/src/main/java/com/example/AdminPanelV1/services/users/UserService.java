package com.example.AdminPanelV1.services.users;

import com.example.AdminPanelV1.domain.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();

    void updateUser(User user);

    void delUser(int id);
}
