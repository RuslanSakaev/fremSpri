package ru.sakaevrs.myFirstProject;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProductEngine implements Engine {
    public ProductEngine() {
        System.out.println("Двигатель запущен на сервере");
    }

    @Override
    public void go() {
        System.out.println("Поехали быстро!");
    }
}
