package ru.sakaev.microServPey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients // Включение использования Feign для облегченного взаимодействия с другими микросервисами
public class MicroServPeyApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroServPeyApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
