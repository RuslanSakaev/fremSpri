package ru.sakaev.microServPey.observer;

public interface Publisher {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(int quantity);
}