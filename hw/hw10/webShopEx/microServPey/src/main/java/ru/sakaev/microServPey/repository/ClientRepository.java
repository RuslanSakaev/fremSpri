package ru.sakaev.microServPey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sakaev.microServPey.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
