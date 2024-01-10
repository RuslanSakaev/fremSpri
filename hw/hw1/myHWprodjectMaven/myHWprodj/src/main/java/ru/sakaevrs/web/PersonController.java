package ru.sakaevrs.web;

import ru.sakaevrs.service.PersonService;
// Импорты для работы с HTTP запросами

public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // Методы для обработки HTTP запросов, например, получение информации о Person
}
