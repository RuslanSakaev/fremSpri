package ru.sakaev.backEndApp.observer;

// Интерфейс для наблюдателя
public interface Observer {
    void update();

    void update(int quantity);
}

