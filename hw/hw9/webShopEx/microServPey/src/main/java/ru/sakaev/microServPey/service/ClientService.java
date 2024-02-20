package ru.sakaev.microServPey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sakaev.microServPey.model.Client;
import ru.sakaev.microServPey.repository.UserRepository;

import java.util.Date;

@Service
public class ClientService {

    @Autowired
    private UserRepository userRepository;

    public Client createClient(String name, double walletAmount) {
        Client user = new Client();
        user.setName(name);
        user.setRegistrationDate(new Date());
        user.setWalletAmount(walletAmount);
        return userRepository.save(user);
    }
}