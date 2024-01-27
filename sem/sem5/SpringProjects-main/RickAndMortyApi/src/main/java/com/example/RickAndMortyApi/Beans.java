package com.example.RickAndMortyApi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Beans {
    /**
     * Кладем HttpHeaders в IoC container
     *
     * @return HttpHeaders
     */
    @Bean
    public HttpHeaders headers() {
        return new HttpHeaders();
    }

    /**
     * Кладем RestTemplate в IoC container
     *
     * @return RestTemplate
     */
    @Bean
    public RestTemplate template() {
        return new RestTemplate();
    }
}
