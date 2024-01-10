package ru.sakaevrs.myFirstProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Car {
    @Autowired
    Engine engine;

    public void start() {
        engine.go();
    }
}

    // 1-й способ вызова через конструктор
    //public Car(Engine engine) {
        //    this.engine = engine;
        //    engine.go();
        //}

    // 2-й способ через сетер
    // внедряем зависимости через конструктор (в 99% случаев)
    //public Car() {

    //}
    //public void setEngine(Engine engine) {
    //    this.engine = engine;
    //    engine.go();
    //}

