package ru.sakaev.SimpleWebAppSMVC.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloWorldApplication {

    // Точка входа в приложение для функциональности приветствия
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }
}