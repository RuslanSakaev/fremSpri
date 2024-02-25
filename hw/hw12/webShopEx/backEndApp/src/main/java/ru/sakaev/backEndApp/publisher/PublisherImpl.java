package ru.sakaev.backEndApp.publisher;

import ru.sakaev.backEndApp.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class PublisherImpl implements Publisher {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
