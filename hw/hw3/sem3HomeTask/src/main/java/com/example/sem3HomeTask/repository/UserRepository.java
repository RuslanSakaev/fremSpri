package com.example.sem3HomeTask.repository;

import com.example.sem3HomeTask.domain.User;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRepository {
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return new ArrayList<>(users); // Возвращаем копию списка для избегания изменений извне
    }

    public void addUser(User user) {
        users.add(user);
    }

    // Дополнительные методы, если необходимы, например, поиск пользователей по критериям
    public List<User> findByAge(int age) {
        return users.stream().filter(user -> user.getAge() == age).collect(Collectors.toList());
    }
}

