package ru.sakaev.microServReserv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sakaev.microServReserv.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
