package ru.sakaev.rickAndMorty.error;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        // Получите код ошибки
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // Обработка различных кодов ошибок
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                // Обработка ошибки 404 (страница не найдена)
                return "error-404"; // Имя вашего шаблона для страницы 404
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                // Обработка внутренней ошибки сервера (ошибка 500)
                return "error-500"; // Имя вашего шаблона для страницы 500
            }
        }
        // Возврат шаблона по умолчанию для других ошибок
        return "error";
    }

    public String getErrorPath() {
        return "/error";
    }
}
