package ru.sakaev.microServReserv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.sakaev.backEndApp.model.Product;
import ru.sakaev.microServReserv.model.Reservation;
import ru.sakaev.microServReserv.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    private final ReservationService reservationService;
    private final RestTemplate restTemplate;
    private final String backendAppUrl; // URL микросервиса backEndApp

    @Autowired
    public ReservationController(ReservationService reservationService, RestTemplate restTemplate, @Value("${backend.app.url}") String backendAppUrl) {
        this.reservationService = reservationService;
        this.restTemplate = restTemplate;
        this.backendAppUrl = backendAppUrl;
    }

    @GetMapping("/status")
    public String getStatus() {
        return "Reservation microservice is up and running!";
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveProduct(@RequestBody Reservation reservation) {
        Long productId = getProductId(); // Получаем ID товара
        if (productId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to get product ID");
        }
        boolean success = reservationService.reserveProduct(productId, reservation.getQuantity());
        if (success) {
            return ResponseEntity.ok("Product reserved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to reserve product");
        }
    }

    @GetMapping("/productId")
    public Long getProductId() {
        try {
            ResponseEntity<List<Product>> response = restTemplate.exchange(
                    backendAppUrl + "/api/products",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Product>>() {}
            );
            if (response.getStatusCode() == HttpStatus.OK) {
                List<Product> productList = response.getBody();
                if (productList != null && !productList.isEmpty()) {
                    return productList.get(0).getId(); // Возвращаем ID первого товара из списка
                }
            }
        } catch (RestClientException ex) {
            // Заменяем printStackTrace() на логгирование
            logger.error("Error occurred while making REST call", ex);
        }
        return null;
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelReservation(@RequestBody Reservation reservation) {
        boolean success = reservationService.cancelReservation(String.valueOf(reservation.getProductId()), reservation.getQuantity());
        if (success) {
            return ResponseEntity.ok("Reservation canceled successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to cancel reservation");
        }
    }
}
