package ru.sakaev.microServReserv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.sakaev.microServReserv.model.Reservation;
import ru.sakaev.microServReserv.repository.ReservationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Метод, который будет вызываться периодически каждые 30 секунд
    @Scheduled(fixedRate = 1000000) // 30 секунд
    @Transactional
    public void checkExpiredReservations() {
        // Получаем список всех резервов
        List<Reservation> reservations = reservationRepository.findAll();

        // Проверяем каждый резерв
        for (Reservation reservation : reservations) {
            // Получаем время создания резерва
            LocalDateTime createdAt = reservation.getCreatedAt();

            // Если резерв не был куплен в течение 30 секунд
            if (createdAt.isBefore(LocalDateTime.now().minusSeconds(1000))) {
                // Возвращаем товар из резерва
                restTemplate.put("http://localhost:8081/api/products/release/" + reservation.getProductId(), null);
                // Удаляем резерв из базы данных
                reservationRepository.delete(reservation);
            }
        }
    }
}
