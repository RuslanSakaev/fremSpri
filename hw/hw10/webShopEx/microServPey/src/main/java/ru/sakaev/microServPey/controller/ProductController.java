package ru.sakaev.microServPey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.sakaev.microServPey.model.ReservationRequest;
import ru.sakaev.microServPey.service.ClientService;
import ru.sakaev.microServPey.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    // Метод для резервирования товара
    @PostMapping("/{productId}/reserve")
    public ResponseEntity<String> reserveProduct(@PathVariable Long productId, @RequestBody ReservationRequest reservationRequest) {
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8083/api/products/" + productId + "/reserve", reservationRequest, String.class);
        return ResponseEntity.ok(response.getBody());
    }

    @PutMapping("/{productId}/release")
    public ResponseEntity<String> releaseProductFromReservation(@PathVariable Long productId, @RequestBody int quantityToRelease) {
        // Отправляем запрос на отмену резервации товара в MicroServReserv
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8083/api/products/" + productId + "/release", HttpMethod.PUT, null, String.class, quantityToRelease);
        return ResponseEntity.ok(response.getBody());
    }
}

