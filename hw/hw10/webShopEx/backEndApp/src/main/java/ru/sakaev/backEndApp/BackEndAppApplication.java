package ru.sakaev.backEndApp; // Объявление пакета для класса BackEndAppApplication

import org.springframework.boot.SpringApplication; // Импорт класса SpringApplication из Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication; // Импорт аннотации SpringBootApplication из Spring Boot
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; // Импорт интерфейса WebMvcConfigurer из Spring Web

@SpringBootApplication // Аннотация, объединяющая аннотации @Configuration, @EnableAutoConfiguration и @ComponentScan
public class BackEndAppApplication implements WebMvcConfigurer { // Объявление класса BackEndAppApplication, реализующего интерфейс WebMvcConfigurer

	public static void main(String[] args) { // Метод main, точка входа в приложение
		SpringApplication.run(BackEndAppApplication.class, args); // Запуск приложения Spring Boot
	}

}
