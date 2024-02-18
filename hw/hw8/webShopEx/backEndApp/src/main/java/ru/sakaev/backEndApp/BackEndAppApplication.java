package ru.sakaev.backEndApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackEndAppApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(BackEndAppApplication.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				.addResourceHandler("/webapp/**")
				.addResourceLocations("classpath:/webapp/");
		registry
				.addResourceHandler("/products.html")
				.addResourceLocations("classpath:/webapp/");
		registry
				.addResourceHandler("/product-list.html")
				.addResourceLocations("classpath:/webapp/");
	}
}