package ru.sakaev.microServReserv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.sakaev.microServReserv.model.Product;
import ru.sakaev.microServReserv.model.Reservation;
import ru.sakaev.microServReserv.model.ReservationRequest;
import ru.sakaev.microServReserv.repository.ProductRepository;
import ru.sakaev.microServReserv.repository.ReservationRepository;

import java.util.List;

@Service
public class ProductReservationService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ReservationRepository reservationRepository;

    @Transactional
    public ResponseEntity<String> reserveProduct(Long productId, ReservationRequest reservationRequest) {
        // Отправляем запрос на получение информации о товаре из BackEndAppApplication
        String backendUrl = "http://localhost:8081/api/products/" + productId;
        ResponseEntity<Product> response = restTemplate.getForEntity(backendUrl, Product.class);
        Product product = response.getBody();

        // Проверяем, хватает ли товара в BackEndAppApplication для резервирования
        if (product != null && product.getQuantity() >= reservationRequest.getQuantity()) {
            // Уменьшаем количество товара в BackEndAppApplication
            product.setQuantity(product.getQuantity() - reservationRequest.getQuantity());
            restTemplate.put(backendUrl, product);

            // Сохраняем товар в резерве в своей базе данных H2
            Reservation reservation = new Reservation();
            reservation.setProductId(productId);
            reservation.setQuantity(reservationRequest.getQuantity());
            reservationRepository.save(reservation);

            return ResponseEntity.ok("Product reserved successfully");
        } else {
            return ResponseEntity.badRequest().body("Not enough product in stock to reserve");
        }
    }

    @Transactional
    public void releaseProduct(Long productId, int quantityToRelease) {
        // Получаем список резервированных товаров для данного productId
        List<Reservation> reservations = reservationRepository.findByProductId(productId);

        // Проверяем, достаточно ли товара в резерве для освобождения
        int totalReservedQuantity = reservations.stream().mapToInt(Reservation::getQuantity).sum();
        if (totalReservedQuantity >= quantityToRelease) {
            // Освобождаем товар из резерва
            for (Reservation reservation : reservations) {
                if (quantityToRelease <= 0) {
                    break;
                }
                int quantityReleased = Math.min(reservation.getQuantity(), quantityToRelease);
                reservation.setQuantity(reservation.getQuantity() - quantityReleased);
                quantityToRelease -= quantityReleased;
                reservationRepository.save(reservation);
            }
        } else {
            throw new RuntimeException("Not enough product reserved to release");
        }
    }
    @Transactional
    public void buyProduct(Long productId, int quantityToBuy) {
        // Извлекаем информацию о резервированных товарах из таблицы RESERVATION
        List<Reservation> reservations = reservationRepository.findByProductId(productId);

        // Считаем общее количество зарезервированных товаров
        int totalReservedQuantity = reservations.stream()
                .mapToInt(Reservation::getQuantity)
                .sum();

        // Проверяем, достаточно ли товара в резерве для покупки
        if (totalReservedQuantity >= quantityToBuy) {
            // Уменьшаем количество товара в резерве
            int remainingQuantityToBuy = quantityToBuy;
            for (Reservation reservation : reservations) {
                int reservedQuantity = reservation.getQuantity();
                if (reservedQuantity >= remainingQuantityToBuy) {
                    reservation.setQuantity(reservedQuantity - remainingQuantityToBuy);
                    reservationRepository.save(reservation);
                    break;
                } else {
                    remainingQuantityToBuy -= reservedQuantity;
                    reservation.setQuantity(0);
                    reservationRepository.save(reservation);
                }
            }
        } else {
            throw new RuntimeException("Not enough product reserved to buy");
        }
    }
}
