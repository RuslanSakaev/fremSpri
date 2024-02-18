package ru.sakaev.microServPey.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import ru.sakaev.microServPey.exception.InsufficientFundsException;
import ru.sakaev.microServPey.model.Wallet;
import ru.sakaev.microServPey.repository.WalletRepository;
import ru.sakaev.microServReserv.model.Product;
import ru.sakaev.microServReserv.repository.ProductRepository;

import java.util.List;

@Service
public class PaymentService {

    private final WalletRepository walletRepository;
    private final ProductRepository productRepository;
    private final WebClient webClient;

    public PaymentService(WalletRepository walletRepository, ProductRepository productRepository, WebClient.Builder webClientBuilder) {
        this.walletRepository = walletRepository;
        this.productRepository = productRepository;
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }

    public void reserveProducts(List<Product> reservedProducts) {
        // Логика сохранения товаров в базу данных H2
        for (Product product : reservedProducts) {
            productRepository.save(product);
        }

        // Отправляем запрос на резервирование товаров в MicroServReservApplication
        webClient.post()
                .uri("/api/products/reserve")
                .body(BodyInserters.fromValue(reservedProducts))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public void confirmPayment(Long userId, double totalPrice) {
        Wallet wallet = walletRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found with userId: " + userId));

        if (wallet.getBalance() >= totalPrice) {
            // Если баланс достаточен, производим оплату и снимаем резерв
            wallet.setBalance(wallet.getBalance() - totalPrice);
            walletRepository.save(wallet);
            // Здесь также можно добавить логику для снятия резерва товаров из MicroServReservApplication
        } else {
            throw new InsufficientFundsException("Insufficient funds in wallet");
        }
    }

    public void cancelPayment(Long userId, double totalReservedPrice) {
        Wallet wallet = walletRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found with userId: " + userId));

        // Возвращаем средства на кошелек
        wallet.setBalance(wallet.getBalance() + totalReservedPrice);
        walletRepository.save(wallet);
        // Здесь можно добавить логику для возврата резерва товаров в MicroServReservApplication
    }
}
