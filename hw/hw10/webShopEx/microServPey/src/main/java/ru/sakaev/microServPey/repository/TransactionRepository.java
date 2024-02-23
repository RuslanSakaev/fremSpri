package ru.sakaev.microServPey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sakaev.microServPey.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
