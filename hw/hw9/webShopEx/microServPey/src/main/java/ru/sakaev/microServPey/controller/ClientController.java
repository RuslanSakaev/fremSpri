package ru.sakaev.microServPey.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.sakaev.microServPey.model.Client;
import ru.sakaev.microServPey.model.ClientRequest;
import ru.sakaev.microServPey.model.ReservationRequest;
import ru.sakaev.microServPey.service.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private RestTemplate restTemplate; // Добавление RestTemplate

    @Autowired
    private ClientService clientService;

    // Вынесенная общая логика для покупки товара
    private ResponseEntity<String> processProductPurchase(Long clientId, ReservationRequest reservationRequest) {
        return clientService.processProductPurchase(clientId, reservationRequest);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody @Valid ClientRequest clientRequest) {
        // Извлекаем имя и сумму кошелька из ClientRequest
        String name = clientRequest.getName();
        double walletAmount = clientRequest.getWalletAmount();

        // Вызываем метод createClient сервиса, передавая имя и сумму кошелька
        Client createdClient = clientService.createClient(name, walletAmount);

        // Возвращаем успешный ответ с созданным клиентом
        return ResponseEntity.ok(createdClient);
    }

    @PostMapping("/{clientId}/buy")
    public ResponseEntity<String> buyProduct(@PathVariable Long clientId, @RequestParam Long productId, @RequestParam int quantity) {
        // Проверяем, что clientId получен корректно
        System.out.println("Received clientId: " + clientId);

        // Создаем объект ReservationRequest с переданными данными
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setClientId(clientId);
        reservationRequest.setProductId(productId);
        reservationRequest.setQuantity(quantity);

        // Отправляем запрос на резервирование товара
        ResponseEntity<String> reserveResponse = restTemplate.postForEntity("http://localhost:8083/api/products/" + productId + "/reserve", reservationRequest, String.class);

        // Выводим сообщение с ответом после запроса на резервирование товара в консоль
        System.out.println("Response from reservation request: " + reserveResponse.getBody());

        // Проверяем ответ на запрос о резервировании товара
        if (reserveResponse.getStatusCode() == HttpStatus.OK) {
            // Резервирование успешно, осуществляем покупку
            return processProductPurchase(clientId, reservationRequest);
        } else {
            // Резервирование не удалось, возвращаем сообщение об ошибке
            return ResponseEntity.status(reserveResponse.getStatusCode()).body(reserveResponse.getBody());
        }
    }

}
