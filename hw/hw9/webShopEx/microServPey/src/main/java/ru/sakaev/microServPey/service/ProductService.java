package ru.sakaev.microServPey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sakaev.microServPey.model.Product;
import ru.sakaev.microServPey.model.ReservationRequest;
import ru.sakaev.microServPey.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Метод для получения ID товара из объекта ReservationRequest
    public Long getProductId(ReservationRequest reservationRequest) {
        return reservationRequest.getProductId();
    }

    // Метод для проверки наличия товара в резерве
    public boolean checkProductReservation(Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElse(null);
        return product != null && product.getQuantity() >= quantity;
    }

    // Метод для завершения покупки товара
    public boolean completePurchase(Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            int newQuantity = product.getQuantity() - quantity;
            if (newQuantity >= 0) {
                // Проверяем, что после покупки количество товара не станет отрицательным
                product.setQuantity(newQuantity);
                productRepository.save(product);
                return true; // Успешно завершено
            } else {
                return false; // Недостаточно товара для покупки
            }
        }
        return false; // Товар не найден
    }


    public double getProductPrice(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        return product.getPrice();
    }
}