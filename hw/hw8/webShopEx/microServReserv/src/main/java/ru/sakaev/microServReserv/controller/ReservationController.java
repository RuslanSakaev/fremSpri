package ru.sakaev.microServReserv.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @GetMapping("/status")
    public String getStatus() {
        return "Reservation microservice is up and running!";
    }
}