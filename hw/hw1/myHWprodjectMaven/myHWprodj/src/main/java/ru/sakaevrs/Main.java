package ru.sakaevrs;

import ru.sakaevrs.domain.Person;
import ru.sakaevrs.service.PersonService;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        // Создание объекта Person
        Person person = new Person("Иван", "Иванов", 30);

        // Логика взаимодействия с сервисным слоем
        PersonService personService = new PersonService();
        // Например, сохранение Person в базу данных
        // personService.savePerson(person);

        // Пример использования Gson для сериализации объекта
        Gson gson = new Gson();
        String json = gson.toJson(person);
        System.out.println("Сериализованный объект в JSON: " + json);

        // Десериализация JSON обратно в объект
        Person personFromJson = gson.fromJson(json, Person.class);
        System.out.println("Десериализованный объект из JSON: " + personFromJson);
    }
}
