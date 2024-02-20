package ru.sakaev.microServPey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sakaev.microServPey.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public boolean checkBalanceAndReserveProduct(double userWalletAmount, double productPrice) {
        // Резервируем товар
        return userWalletAmount >= productPrice;
    }
}