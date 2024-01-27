package com.example.AdminPanelV1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class UsersControlsApp {

	public static void main(String[] args) {
		SpringApplication.run(UsersControlsApp.class, args);
	}

}
