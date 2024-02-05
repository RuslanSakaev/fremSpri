package ru.sakaevrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sakaevrs.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}