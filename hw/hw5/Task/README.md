# Фреймворк Spring (семинары)
## Урок 5. Spring Data для работы с базами данных
### Базовое задание:
#### Условие:
- Вам предстоит создать приложение для управления списком задач с использованием Spring Boot и Spring Data JPA. 
-  Требуется реализовать следующие функции:

- Добавление задачи. 
- - Подсказка метод в контроллере:
- - - @PostMapping public Task addTask(@RequestBody Task task)
- Просмотр всех задач.
- - Подсказка метод в контроллере:
- - - @GetMapping public List<Task> getAllTasks()
- Просмотр задач по статусу (например, "завершена", "в процессе", "не начата"). 
- - Подсказка метод в контроллере: 
- - - @GetMapping("/status/{status}") 
- - - public List<Task> 
- - - getTasksByStatus(@PathVariable TaskStatus status)
- Изменение статуса задачи. 
- - Подсказка метод в контроллере: 
- - - @PutMapping("/{id}") public Task 
- - - updateTaskStatus(@PathVariable Long id, 
- - - @RequestBody Task task)
- Удаление задачи.
- - Подсказка метод в контроллере:
- - - @DeleteMapping("/{id}")
- - - public void deleteTask(@PathVariable Long id)
- - - Репозитроий подсказка public interface TaskRepository extends JpaRepository<Task, Long>

### Структура задачи(класс Task):
- ID (автоинкрементное)(тип Long)
- Описание (не может быть пустым)(тип String)
- Статус (одно из значений: "не начата", "в процессе", "завершена")(Тип TaskStatus )
- Дата создания (автоматически устанавливается при создании задачи)(Тип LocalDateTime)
- Подсказка понадобится энумератор:
- - enum TaskStatus {
NOT_STARTED, IN_PROGRESS, COMPLETED;
}

# Решение
- Файл application.properties
```
Thymeleaf settings:

spring.thymeleaf.cache=false: Отключает кэширование шаблонов Thymeleaf для удобства разработки.
spring.thymeleaf.enabled=true: Включает использование Thymeleaf в приложении.
spring.thymeleaf.prefix=classpath:/templates/: Указывает Thymeleaf, где искать шаблоны. В данном случае, они расположены в папке templates в classpath.
spring.thymeleaf.suffix=.html: Указывает расширение файлов шаблонов.
Database settings:

spring.datasource.url=jdbc:h2:mem:testdb: Указывает URL для базы данных H2 в памяти.
spring.datasource.driverClassName=org.h2.Driver: Указывает класс драйвера базы данных H2.
spring.datasource.username=sa: Указывает имя пользователя для подключения к базе данных.
spring.datasource.password=password: Указывает пароль для подключения к базе данных.
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect: Указывает Hibernate использовать H2Dialect для работы с базой данных H2.
```
- Архитектура проекта
```
src
-- main
    -- java
        -- ru.sakev.Task
            -- domain
               -- Task.java
               -- TaskStatus.java
            -- repository
               -- TaskRepository.java
            -- controllers
               -- TaskController.java
            -- service
               -- TaskService.java
            -- TaskApplication.java
    -- resources
        -- templates
            -- tasks.html
        -- application.properties
```
