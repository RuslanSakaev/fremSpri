package ru.sakaev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import ru.sakaev.apiGateway.ApiGatewayApplication;
import ru.sakaev.backEndApp.BackEndAppApplication;
import ru.sakaev.eurekaServer.EurekaServerApplication;
import ru.sakaev.microServPey.MicroServPeyApplication;
import ru.sakaev.microServReserv.MicroServReservApplication;

import java.util.Collections;

@SpringBootApplication
public class MainApplication {
	private static final Logger logger = LoggerFactory.getLogger(MainApplication.class);

	public static void main(String[] args) {
		// Запуск каждого приложения на разных портах
		startApplication(BackEndAppApplication.class, 8081);
		startApplication(MicroServPeyApplication.class, 8082);
		startApplication(MicroServReservApplication.class, 8083);
		startApplication(EurekaServerApplication.class, 8761);
		startApplication(ApiGatewayApplication.class, 8080);
	}

	// Метод для запуска приложения в отдельном потоке с указанным портом
	private static void startApplication(Class<?> applicationClass, int port) {
		Thread thread = new Thread(() -> {
			// Создание нового Spring приложения
			SpringApplication app = new SpringApplication(applicationClass);
			// Установка порта для Spring приложения
			app.setDefaultProperties(Collections.singletonMap("server.port", String.valueOf(port)));
			// Запуск Spring приложения
			app.run();
		});
		// Запуск потока
		thread.start();
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
