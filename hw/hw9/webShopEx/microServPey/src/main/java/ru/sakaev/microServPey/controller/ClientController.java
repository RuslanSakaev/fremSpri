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
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
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

    // Метод для резервирования товара и покупки
    @PostMapping("/{clientId}/buy")
    public ResponseEntity<String> buyProduct(@PathVariable Long clientId, @RequestParam Long productId, @RequestParam int quantity) {
        // Создаем объект ReservationRequest с переданными данными
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setClientId(clientId);
        reservationRequest.setProductId(productId);
        reservationRequest.setQuantity(quantity);

        // Вызываем метод сервиса для покупки товара
        return clientService.processProductPurchase(clientId, reservationRequest);
    }
}
