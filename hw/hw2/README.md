# Фреймворк Spring (семинары)
## Урок 2. Основы Spring. Spring Boot
### Базовое задание:
Добавить в простое CRUD веб-приложение, которое было разработано на семинаре функцию удаления данных о пользователе:
1) В класс UserRepository добавить метод public void deleteById(int id)(подсказка: SQL запрос "DELETE FROM userTable WHERE id=?", метод jdbc.update) - удаления записи пользователя из БД по ID.
2) В класс UserService добавить метод public void deleteById(int id)(подсказка: в нем вызывается метод userRepository.deleteById) - удаление пользователя через репозиторий.
3) В класс UserController добавить метод public String deleteUser(@PathVariable("id") int id)(с аннотацией: @GetMapping("user-delete/{id}")) (подсказка: в нем вызывается метод userService.deleteById) - перехват команды на удаление студента от браузера.

Если задание выполнено верно, то при запуске приложения по адресу http://localhost:8080/users будет работать кнопка удаления пользователя по ID.

### Задание "со звездочкой":
Реализовать метод обновления данных о пользователе.
- @GetMapping("/user-update/{id}")
- @PostMapping("/user-update")
- User update(User user)
- User getOne(int id)

# Решение: 
https://github.com/RuslanSakaev/fremSpri/tree/master/sem/sem2/CRUDSpringWeb/demo
#### Для реализации функции удаления данных о пользователе добавим методы в классы UserRepository, UserService и UserController:
```java
public class UserRepository {
    public void deleteById(int id) {
        String sql = "DELETE FROM userTable WHERE id=?";
        jdbc.update(sql, id);
    }
}
```
```java
public class UserService {
    private final UserRepository userRepository;
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}
```
```java
public class UserController {
    private final UserService userService;
    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}
```
- Эти методы позволят обрабатывать запросы на удаление пользователя по идентификатору. 
- При переходе по _URL /user-delete/{id}_ пользователь с указанным ID будет удален из базы данных, а пользователь будет перенаправлен обратно на список пользователей.

#### Чтобы реализовать возможность обновления данных о пользователе, добавим следующие методы:
1. В класс UserService:
```java
    public User update(User user) {
        return userRepository.update(user);
    }

    public User getOne(int id) {
        return userRepository.getOne(id);
    }
```
2. В класс UserRepository:
```java
    public User update(User user) {
        String sql = "UPDATE userTable SET firstName = ?, lastName = ? WHERE id = ?";
        jdbc.update(sql, user.getFirstName(), user.getLastName(), user.getId());
        return user;
    }

    public User getOne(int id) {
        String sql = "SELECT * FROM userTable WHERE id = ?";
        return jdbc.queryForObject(sql, new Object[]{id}, (r, i) -> {
            User user = new User();
            user.setId(r.getInt("id"));
            user.setFirstName(r.getString("firstName"));
            user.setLastName(r.getString("lastName"));
            return user;
        });
    }
```
3. В класс UserController:
```java
    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        User user = userService.getOne(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user) {
        userService.update(user);
        return "redirect:/users";
    }
```
- Добавлены методы для обновления данных о пользователе _(updateUserForm, updateUser)_ в _UserController_.
- Метод _update_ также добавлен в _UserService_, и _update_ в _UserRepository_ выполняет обновление записи в базе данных.
- Дополнительно, добавлен метод _getOne_ в _UserRepository_, который возвращает пользователя по его ID.

1. UserController:
   - updateUserForm(int id, Model model)
   - Path: @GetMapping("/user-update/{id}")
   - Параметры:
   - - id: Идентификатор пользователя, данные которого необходимо обновить.
   - - model: Объект Model из Spring MVC, используется для передачи данных между контроллером и представлением.
   - Описание: 
   - - Этот метод вызывается при получении GET-запроса на URL /user-update/{id}. Он извлекает пользователя по ID с помощью userService.getOne(id) и добавляет этого пользователя в модель (model.addAttribute("user", user)). Затем он возвращает имя представления (например, HTML-шаблона), которое будет использоваться для отображения формы обновления данных пользователя.
   - updateUser(User user)
   - Path: @PostMapping("/user-update")
   - Параметры:
   - - user: Объект User, содержащий обновленные данные пользователя. Spring автоматически связывает поля формы с этим объектом.
   - - Описание: Этот метод вызывается при получении POST-запроса на URL /user-update. Он обновляет данные пользователя в базе данных, используя userService.update(user). После успешного обновления, метод перенаправляет пользователя на страницу со списком всех пользователей ("redirect:/users").
2. UserService:
   - update(User user)
   - Параметры:
   - - user: Объект User, содержащий обновленные данные.
   - Описание: 
   - - Этот метод вызывается из UserController. Он просто делегирует вызов методу update в UserRepository, который фактически обновляет данные в базе данных.
   - getOne(int id)
   - Параметры:
   - - id: Идентификатор пользователя.
   - Описание: Возвращает пользователя по его ID. Метод делегирует вызов getOne методу в UserRepository.
3. UserRepository:
   - update(User user)
   - - SQL: "UPDATE userTable SET firstName = ?, lastName = ? WHERE id = ?"
   - Параметры:
   - - user: Объект User, содержащий обновленные данные.
   - Описание: 
   - - Этот метод выполняет SQL-запрос для обновления записи пользователя в базе данных. Он использует значения firstName, lastName из объекта User и его id для определения, какую запись обновить.
   - getOne(int id)
   - - SQL: "SELECT * FROM userTable WHERE id = ?"
   - Параметры:
   - - id: Идентификатор пользователя.
   - Описание: 
   - - Выполняет SQL-запрос для получения пользователя по его ID из базы данных. Возвращаемый результат маппится в объект User, который затем возвращается вызывающему методу.
4. Эти методы в совокупности обеспечивают функциональность для обновления данных о пользователях. 
Когда пользователь захочет обновить данные, он сначала перейдет на форму обновления (обрабатывается updateUserForm), затем отправит обновленные данные (обрабатывается updateUser), после чего данные будут обновлены в базе данных и пользователь будет перенаправлен на страницу со списком всех пользователей.
