package ru.sakaev.backEndApp.publisher;

import ru.sakaev.backEndApp.observer.Observer;

public interface Publisher {
    void subscribe(Observer observer);
    void unsubscribe(Observer observer);
    void notifyObservers();
}