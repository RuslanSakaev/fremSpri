package ru.sakaevrs;

import com.google.gson.Gson;
public class Main {
    public static void main(String[] args) {
        // Создание объекта Person
        Person person = new Person("Иван", "Иванов", 30);

        // Создание экземпляра Gson
        Gson gson = new Gson();

        // Сериализация (конвертация объекта в JSON)
        String json = gson.toJson(person);
        System.out.println("Сериализованный объект в JSON: " + json);

        // Десериализация (конвертация JSON обратно в объект)
        Person personFromJson = gson.fromJson(json, Person.class);
        System.out.println("Десериализованный объект из JSON: " + personFromJson);
    }
}

