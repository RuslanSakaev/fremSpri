package ru.sakaev.Task.service;

import ru.sakaev.Task.domain.Task;
import ru.sakaev.Task.domain.TaskStatus;
import ru.sakaev.Task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        // Конструктор сервиса, принимающий TaskRepository в качестве зависимости.
        this.taskRepository = taskRepository;
    }

    public Task addTask(Task task) {
        // Метод для добавления задачи. Устанавливает текущую дату создания и сохраняет задачу в репозитории.
        task.setCreationDate(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        // Метод для получения списка всех задач из репозитория.
        return taskRepository.findAll();
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        // Метод для получения списка задач по их статусу из репозитория.
        return taskRepository.findByStatus(status);
    }

    public Task updateTaskStatus(Long id, TaskStatus status) {
        // Метод для обновления статуса задачи по её идентификатору.
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setStatus(status);
            return taskRepository.save(task);
        }
        return null;
    }

    public void deleteTask(Long id) {
        // Метод для удаления задачи по её идентификатору.
        try {
            taskRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println("Error deleting task with id " + id + ": " + e.getMessage());
        }
    }
}
