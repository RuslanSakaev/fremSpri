package ru.sakaev.microServPey.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sakaev.backEndApp.observer.ProductQuantityPublisher;
import ru.sakaev.microServPey.component.ProductQuantityObserver;

@Configuration
public class ObserverConfiguration {

    @Bean
    public ProductQuantityObserver productQuantityObserver() {
        return new ProductQuantityObserver();
    }

    @Bean
    public ProductQuantityPublisher productQuantityPublisher() {
        return new ProductQuantityPublisher();
    }
}
