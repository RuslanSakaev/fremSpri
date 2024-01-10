package ru.sakaevrs.myFirstProject;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class LocalEngine implements Engine {
    public LocalEngine() {
        System.out.println("Двигатель запущен на моём ноутбуке");
    }

    @Override
    public void go() {
        System.out.println("Поехали медленно!");
    }
}
