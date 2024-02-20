package ru.sakaev.microServPey.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sakaev.microServPey.model.Transaction;
import ru.sakaev.microServPey.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public void addTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}