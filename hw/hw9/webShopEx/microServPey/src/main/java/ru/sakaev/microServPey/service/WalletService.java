package ru.sakaev.microServPey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sakaev.microServPey.repository.WalletRepository;

@Service
public class WalletService {

    @Autowired
    public WalletService(WalletRepository walletRepository) {
    }

    // Другие методы для работы с кошельками
}