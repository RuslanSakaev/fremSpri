package ru.sakaev.microServPey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.sakaev.microServPey.model.Product;
import ru.sakaev.microServPey.model.ReservationRequest;
import ru.sakaev.microServPey.model.Transaction;
import ru.sakaev.microServPey.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionService transactionService;

    // Метод для проверки наличия товара в резерве
    public boolean checkProductReservation(Long productId, int quantity) {
        Product product = getProductById(productId);
        return product != null && product.getQuantity() >= quantity;
    }

    // Метод для завершения покупки товара
    public ResponseEntity<String> completePurchase(Long productId, int quantity, Long clientId) {
        try {
            Product product = getProductById(productId);
            if (product != null) {
                int newQuantity = product.getQuantity() - quantity;
                if (newQuantity >= 0) {
                    createTransaction(clientId, productId, quantity, product.getPrice());
                    updateProductQuantity(productId, newQuantity);
                    return ResponseEntity.ok("Покупка товара успешно завершена");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Недостаточно товара для покупки");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Товар с ID " + productId + " не найден");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при покупке товара: " + e.getMessage());
        }
    }

    // Метод для создания транзакции оплаты
    private void createTransaction(Long clientId, Long productId, int quantity, double price) {
        Transaction transaction = new Transaction(clientId, productId, quantity, price);
        transactionService.addTransaction(transaction);
    }

    // Метод для обновления количества товара после покупки
    private void updateProductQuantity(Long productId, int newQuantity) {
        Product product = getProductById(productId);
        product.setQuantity(newQuantity);
        productRepository.save(product);
    }

    // Метод для получения цены товара по его ID
    public double getProductPrice(Long productId) {
        Product product = getProductById(productId);
        return product != null ? product.getPrice() : 0.0;
    }

    // Метод для получения товара по его ID
    private Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }
}
