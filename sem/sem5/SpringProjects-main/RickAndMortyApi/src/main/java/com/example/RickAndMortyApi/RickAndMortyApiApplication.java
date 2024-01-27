package com.example.RickAndMortyApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RickAndMortyApiApplication {
	/**
	 * Инкапсулируем бины для связи с внешним Api
	 */
	@Autowired
	Beans bean;

	public static void main(String[] args) {
		SpringApplication.run(RickAndMortyApiApplication.class, args);
	}
}
