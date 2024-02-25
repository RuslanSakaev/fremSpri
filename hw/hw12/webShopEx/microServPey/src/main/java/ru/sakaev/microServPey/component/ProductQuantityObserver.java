package ru.sakaev.microServPey.component;

import org.springframework.stereotype.Component;

@Component
public class ProductQuantityObserver implements ProductObserver {
    @Override
    public void update(int quantity) {
        // Обработка уведомления об изменении количества продуктов
        System.out.println("Received product quantity update: " + quantity);
        // Здесь можно добавить логику для покупки товаров и т.д.
    }
}
