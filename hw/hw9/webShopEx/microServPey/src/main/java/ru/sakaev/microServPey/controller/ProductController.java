package ru.sakaev.microServPey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.sakaev.microServPey.model.ReservationRequest;
import ru.sakaev.microServPey.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductService productService;

    @PostMapping("/{productId}/reserve")
    public ResponseEntity<String> reserveProduct(@PathVariable Long productId, @RequestBody ReservationRequest reservationRequest) {
        try {
            // Отправляем запрос на резервирование товара в MicroServReserv
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8083/api/products/" + productId + "/reserve", reservationRequest, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{productId}/buy")
    public ResponseEntity<String> buyProduct(@PathVariable Long productId, @RequestBody ReservationRequest reservationRequest) {
        try {
            // Отправляем запрос на покупку товара в MicroServReserv
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8083/api/products/" + productId + "/buy", reservationRequest, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{productId}/release")
    public ResponseEntity<String> releaseProductFromReservation(@PathVariable Long productId, @RequestBody int quantityToRelease) {
        try {
            // Отправляем запрос на отмену резервации товара в MicroServReserv
            ResponseEntity<String> response = restTemplate.exchange("http://localhost:8083/api/products/" + productId + "/release", HttpMethod.PUT, null, String.class, quantityToRelease);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
