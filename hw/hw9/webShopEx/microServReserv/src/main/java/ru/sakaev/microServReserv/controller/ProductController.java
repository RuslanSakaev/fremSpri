package ru.sakaev.microServReserv.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.sakaev.microServReserv.model.ReservationRequest;
import ru.sakaev.microServReserv.repository.ReservationRepository;
import ru.sakaev.microServReserv.service.ProductReservationService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductReservationService productReservationService; // Внедрение сервиса

    @Autowired
    private ReservationRepository reservationRepository;
    @PostMapping("/{productId}/reserve")
    public ResponseEntity<String> reserveProduct(@PathVariable Long productId, @RequestBody ReservationRequest reservationRequest) {
        try {
            // Вызываем метод сервиса для резервирования товара
            return productReservationService.reserveProduct(productId, reservationRequest);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/{productId}/buy")
    public ResponseEntity<String> buyProduct(@PathVariable Long productId, @RequestBody ReservationRequest reservationRequest) {
        try {
            // Вызов сервиса для покупки товара
            productReservationService.buyProduct(productId, reservationRequest.getQuantity());
            return ResponseEntity.ok("Product purchased successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
