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

    // Вынесенная общая логика для покупки товара
    private ResponseEntity<String> processProductPurchase(Long clientId, ReservationRequest reservationRequest) {
        return clientService.processProductPurchase(clientId, reservationRequest);
    }

    @PostMapping("/{productId}/reserve")
    public ResponseEntity<String> reserveProduct(@PathVariable Long productId, @RequestBody ReservationRequest reservationRequest) {
        // Отправляем запрос на резервирование товара в MicroServReserv
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8083/api/products/" + productId + "/reserve", reservationRequest, String.class);
        return ResponseEntity.ok(response.getBody());
    }

    @PostMapping("/{productId}/buy")
    public ResponseEntity<String> buyProduct(@PathVariable Long productId, @RequestParam Long clientId, @RequestParam int quantity) {
        // Создаем объект ReservationRequest с переданными данными
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setClientId(clientId);
        reservationRequest.setProductId(productId);
        reservationRequest.setQuantity(quantity);

        // Отправляем запрос на резервирование товара в MicroServReserv
        ResponseEntity<String> reserveResponse = restTemplate.postForEntity("http://localhost:8083/api/products/" + productId + "/reserve", reservationRequest, String.class);

        // Проверяем ответ на запрос о резервировании товара
        if (reserveResponse.getStatusCode() == HttpStatus.OK) {
            // Резервирование успешно, осуществляем покупку
            return processProductPurchase(clientId, reservationRequest);
        } else {
            // Резервирование не удалось, возвращаем сообщение об ошибке
            return ResponseEntity.status(reserveResponse.getStatusCode()).body(reserveResponse.getBody());
        }
    }

    @PutMapping("/{productId}/release")
    public ResponseEntity<String> releaseProductFromReservation(@PathVariable Long productId, @RequestBody int quantityToRelease) {
        // Отправляем запрос на отмену резервации товара в MicroServReserv
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8083/api/products/" + productId + "/release", HttpMethod.PUT, null, String.class, quantityToRelease);
        return ResponseEntity.ok(response.getBody());
    }
}
