package com.example.AdminPanelV1.controllers.WEB;

import com.example.AdminPanelV1.domain.User;
import com.example.AdminPanelV1.services.users.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@AllArgsConstructor
@Log
@Controller
public class LoginController {
    /**
     * Сервис реализации логики управления пользователями
     */
    private UserServiceImpl userService;

    /**
     * Открытие главной страницы входа
     *
     * @return
     */
    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }

    /**
     * Обработка входа пользователя в систему
     *
     * @param email    почта из браузера
     * @param password пароль из браузера
     * @return если авторизация успешна - откроется админ анель, если нет - страница ошибки.
     */
    @GetMapping("/check-login")
    public String checkLogin(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             Model model) {
        try {
            User user = userService.getAllUsers().stream()
                    .filter(item ->
                    (item.getEmail().equals(email) && item.getPassword().equals(password)))
                    .findFirst().get();

            if (user.isAdmin()) {
                userService.loginAdmin(user, true);
                model.addAttribute("isLogin", "online");
                model.addAttribute("idLogout", user.getId());
                return "redirect:/users-db/" + user.getId();
            } else return "404";

        } catch (Exception e) {
            return "404";
        }
    }

    @GetMapping("/logout")
    public String logout(){
        List<User> users = userService.getAllUsers();
        User user = users.stream().filter(User::isLogin).findFirst().get();
        userService.loginAdmin(user, false);
//        user.setLogin(false);
        return "redirect:/main";
    }
}
