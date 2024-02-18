package ru.sakaev.microServPey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sakaev.microServPey.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}