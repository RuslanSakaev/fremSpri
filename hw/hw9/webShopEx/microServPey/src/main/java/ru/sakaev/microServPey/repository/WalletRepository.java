package ru.sakaev.microServPey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sakaev.microServPey.model.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
}