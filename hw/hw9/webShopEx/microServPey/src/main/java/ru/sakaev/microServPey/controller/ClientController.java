package ru.sakaev.microServPey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sakaev.microServPey.model.Client;
import ru.sakaev.microServPey.service.ClientService;

@RestController
@RequestMapping("/api/users")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public Client createClient(@RequestParam String name, @RequestParam double walletAmount) {
        return clientService.createClient(name, walletAmount);
    }
}
