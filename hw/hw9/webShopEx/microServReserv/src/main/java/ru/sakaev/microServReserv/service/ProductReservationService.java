package ru.sakaev.microServReserv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sakaev.microServReserv.model.Product;
import ru.sakaev.microServReserv.repository.ProductRepository;

@Service
public class ProductReservationService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void reserveProduct(Long productId, int quantityToReserve) {
        // Получаем товар из базы данных H2 по его ID
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        // Проверяем, достаточно ли товара на складе для резервирования
        if (product.getQuantity() >= quantityToReserve) {
            // Уменьшаем количество товара на складе
            product.setQuantity(product.getQuantity() - quantityToReserve);
            // Увеличиваем количество товара в резерве
            product.setReservedQuantity(product.getReservedQuantity() + quantityToReserve);
            // Сохраняем изменения в базе данных H2
            productRepository.save(product);
        } else {
            throw new RuntimeException("Not enough product in stock to reserve");
        }
    }

    @Transactional
    public void releaseProduct(Long productId, int quantityToRelease) {
        // Получаем товар из базы данных H2 по его ID
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        // Проверяем, достаточно ли товара в резерве для освобождения
        if (product.getReservedQuantity() >= quantityToRelease) {
            // Уменьшаем количество товара в резерве
            product.setReservedQuantity(product.getReservedQuantity() - quantityToRelease);
            // Увеличиваем количество товара на складе
            product.setQuantity(product.getQuantity() + quantityToRelease);
            // Сохраняем изменения в базе данных H2
            productRepository.save(product);
        } else {
            throw new RuntimeException("Not enough product reserved to release");
        }
    }
}
