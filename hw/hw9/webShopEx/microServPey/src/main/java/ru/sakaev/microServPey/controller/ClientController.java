package ru.sakaev.microServPey.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sakaev.microServPey.model.Client;
import ru.sakaev.microServPey.model.ClientRequest;
import ru.sakaev.microServPey.model.ReservationRequest;
import ru.sakaev.microServPey.service.ClientService;
import ru.sakaev.microServPey.service.ProductService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody @Valid ClientRequest clientRequest) {
        // Извлечь имя и сумму кошелька из ClientRequest
        String name = clientRequest.getName();
        double walletAmount = clientRequest.getWalletAmount();

        // Вызвать метод createClient сервиса, передавая имя и сумму кошелька
        Client createdClient = clientService.createClient(name, walletAmount);

        // Вернуть успешный ответ с созданным клиентом
        return ResponseEntity.ok(createdClient);
    }

    @Autowired
    private ProductService productService;

    // Метод для обработки запроса на покупку товара клиентом
    @PostMapping("/{clientId}/buy")
    public ResponseEntity<String> buyProduct(@PathVariable Long clientId, @RequestBody ReservationRequest reservationRequest) {
        return clientService.processProductPurchase(clientId, reservationRequest);
    }
}
