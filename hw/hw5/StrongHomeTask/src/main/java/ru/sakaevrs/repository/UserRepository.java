package ru.sakaevrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sakaevrs.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}