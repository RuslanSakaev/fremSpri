package ru.sakaev.microServReserv.service;

import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    public void reserveItem(String itemId, int quantity) {
        System.out.println("Reserving " + quantity + " item(s) with ID " + itemId);
    }
}