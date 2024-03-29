package ru.sakaev.backEndApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackEndAppApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(BackEndAppApplication.class, args);
	}

}