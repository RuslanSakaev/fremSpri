package ru.sakaev.microServPey.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.sakaev.microServPey.model.Client;
import ru.sakaev.microServPey.model.ReservationRequest;
import ru.sakaev.microServPey.repository.ClientRepository;

import java.util.Date;

@Service
public class ClientService {
    private static final Logger logger = LogManager.getLogger(ClientService.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientRepository clientRepository;

    // Метод для обработки запроса на покупку товара клиентом
    public ResponseEntity<String> processProductPurchase(Long clientId, ReservationRequest reservationRequest) {
        logger.info("Обработка покупки товара для ID клиента: {}", clientId);
        try {
            // Получаем клиента по ID
            Client client = clientRepository.findById(clientId)
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);

            // Получаем информацию о товаре из запроса на покупку
            Long productId = reservationRequest.getProductId();
            int quantity = reservationRequest.getQuantity();

            // Проверяем, доступен ли товар для покупки
            boolean productReserved = productService.checkProductReservation(productId, quantity);
            if (!productReserved) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Товар с ID " + productId + " не доступен для покупки");
            }

            // Проверяем, достаточно ли средств в кошельке клиента
            double totalPrice = productService.getProductPrice(productId) * quantity;
            if (client.getWalletAmount() < totalPrice) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Недостаточно средств в кошельке для покупки");
            }

            // Если все проверки пройдены успешно, завершаем покупку товара
            ResponseEntity<String> purchaseResponse = productService.completePurchase(productId, quantity, clientId);
            return ResponseEntity.ok(purchaseResponse.getBody());
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Ошибка при обработке покупки товара: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при покупке товара: " + e.getMessage());
        }
    }

    // Метод для создания клиента
    public Client createClient(String name, double walletAmount) {
        Client client = new Client();
        client.setName(name);
        client.setRegistrationDate(new Date());
        client.setWalletAmount(walletAmount);
        return clientRepository.save(client);
    }

}
