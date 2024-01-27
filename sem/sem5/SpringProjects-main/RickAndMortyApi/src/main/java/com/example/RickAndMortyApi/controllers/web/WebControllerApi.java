package com.example.RickAndMortyApi.controllers.web;

import com.example.RickAndMortyApi.services.ServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Контроллер для взаимодействия в web браузере
 */
@Controller
public class WebControllerApi {
    /**
     * Инкапсулируем сервис (интерфейс) для работы с данными
     */
    @Autowired
    private ServiceApi serviceApi;

    /**
     * Главная страница
     *
     * @param model связь с шаблонизатором
     * @return index.html
     */
    @GetMapping("/")
    public String getMain(Model model) {
        model.addAttribute("results", serviceApi.getAllCharacters().getResults());
        return "index";
    }

    /**
     * Фильтрация по статусу "alive"
     *
     * @param model связь с шаблонизатором
     * @return index.html
     */
    @GetMapping("/alive")
    public String getAlive(Model model) {
        model.addAttribute("results", serviceApi.getByStatus("Alive"));
        return "index";
    }

    /**
     * Фильтрация по статусу "Dead"
     *
     * @param model связь с шаблонизатором
     * @return index.html
     */
    @GetMapping("/dead")
    public String getDead(Model model) {
        model.addAttribute("results", serviceApi.getByStatus("Dead"));
        return "index";
    }

    /**
     * Фильтрация по статусу "unknown"
     *
     * @param model связь с шаблонизатором
     * @return index.html
     */
    @GetMapping("/unknown")
    public String getUnknown(Model model) {
        model.addAttribute("results", serviceApi.getByStatus("unknown"));
        return "index";
    }

    /**
     * Поиск по имени
     *
     * @param model связь с шаблонизатором
     * @param name  передаваемое имя из браузера input
     * @return index.html
     */
    @GetMapping("/search")
    public String getByName(Model model, @RequestParam("name") String name) {
        model.addAttribute("results", serviceApi.getByName(name));
        return "index";
    }
}
