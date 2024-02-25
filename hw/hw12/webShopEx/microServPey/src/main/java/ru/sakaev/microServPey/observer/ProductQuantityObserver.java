package ru.sakaev.microServPey.observer;

import org.springframework.stereotype.Component;

@Component
public class ProductQuantityObserver implements Observer {
    @Override
    public void update(int quantity) {
        // Обработка уведомления об изменении количества продуктов
        System.out.println("Received product quantity update: " + quantity);
        // Здесь можно добавить логику для покупки товаров и т.д.
    }
}