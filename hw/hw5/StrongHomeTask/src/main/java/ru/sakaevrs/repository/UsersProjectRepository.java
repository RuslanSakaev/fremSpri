package ru.sakaevrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import ru.sakaevrs.model.UsersProject;

public interface UsersProjectRepository extends JpaRepository<UsersProject, Long> {
    Optional<List<UsersProject>> findByProjectId(Long projectId);
    Optional<List<UsersProject>> findByUserId(Long userId);
    Optional<UsersProject> findByUserIdAndProjectId(Long userId, Long projectId);
}

