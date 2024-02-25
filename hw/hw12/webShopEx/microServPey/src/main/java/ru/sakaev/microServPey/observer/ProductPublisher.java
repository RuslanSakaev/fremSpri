package ru.sakaev.microServPey.observer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductPublisher implements Publisher {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(int quantity) {
        for (Observer observer : observers) {
            observer.update(quantity);
        }
    }

    // Метод, который будет вызываться для уведомления наблюдателей об изменении количества
    public void productQuantityChanged(int newQuantity) {
        notifyObservers(newQuantity);
    }
}