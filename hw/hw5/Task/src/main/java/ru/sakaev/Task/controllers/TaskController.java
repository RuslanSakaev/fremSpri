package ru.sakaev.Task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sakaev.Task.domain.Task;
import ru.sakaev.Task.domain.TaskStatus;
import ru.sakaev.Task.service.TaskService;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Обработка GET-запроса на получение всех задач.
    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    // Обработка GET-запроса на получение задач по статусу.
    @GetMapping("/status/{status}")
    public String getTasksByStatus(@PathVariable TaskStatus status, Model model) {
        List<Task> tasks = taskService.getTasksByStatus(status);
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    // Обработка POST-запроса на добавление новой задачи.
    @PostMapping
    public String addTask(@ModelAttribute Task task, Model model) {
        taskService.addTask(task);
        return "redirect:/tasks";
    }

    // Обработка PUT-запроса на обновление статуса задачи.
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> updateTaskStatus(@PathVariable Long id, @RequestParam TaskStatus status) {
        Task task = taskService.updateTaskStatus(id, status);
        return task != null ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Обработка DELETE-запроса на удаление задачи.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
