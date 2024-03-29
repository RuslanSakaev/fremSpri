package ru.sakaev.microServPey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sakaev.microServPey.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    double getProductPriceById(Long productId);
}
