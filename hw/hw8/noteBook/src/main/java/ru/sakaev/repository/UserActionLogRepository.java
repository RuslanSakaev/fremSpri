package ru.sakaev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sakaev.model.UserActionLog;

@Repository
public interface UserActionLogRepository extends JpaRepository<UserActionLog, Long> {
    // Дополнительные методы для работы с записями журнала действий пользователя, если необходимо
}
