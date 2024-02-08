package ru.sakaev.rickAndMorty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RickAndMortyApplication {



	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public HttpHeaders headers()
	{
		return new org.springframework.http.HttpHeaders();
	}
	public static void main(String[] args) {
		SpringApplication.run(RickAndMortyApplication.class, args);
	}

}
