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

    /**
     * Обрабатывает запрос на покупку товара клиентом.
     *
     * @param clientId            ID клиента
     * @param reservationRequest  информация о заказе товара
     * @return ResponseEntity со статусом и сообщением о результате покупки
     */
    public ResponseEntity<String> processProductPurchase(Long clientId, ReservationRequest reservationRequest) {
        logger.info("Обработка покупки товара для ID клиента: {}", clientId);
        try {
            Client client = getClientById(clientId);
            Long productId = reservationRequest.getProductId();
            int quantity = reservationRequest.getQuantity();
            boolean productReserved = checkProductAvailability(productId, quantity);
            if (!productReserved) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Товар с ID " + productId + " не доступен для покупки");
            }
            double totalPrice = calculateTotalPrice(productId, quantity);
            if (client.getWalletAmount() < totalPrice) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Недостаточно средств в кошельке для покупки");
            }
            ResponseEntity<String> purchaseResponse = productService.completePurchase(productId, quantity, clientId);
            return ResponseEntity.ok(purchaseResponse.getBody());
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Ошибка при обработке покупки товара: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при покупке товара: " + e.getMessage());
        }
    }

    /**
     * Получает клиента по его ID.
     *
     * @param clientId ID клиента
     * @return найденный клиент
     * @throws ChangeSetPersister.NotFoundException если клиент не найден
     */
    private Client getClientById(Long clientId) throws ChangeSetPersister.NotFoundException {
        return clientRepository.findById(clientId).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    /**
     * Проверяет доступность товара для покупки.
     *
     * @param productId ID товара
     * @param quantity  количество товара
     * @return true, если товар доступен для покупки, в противном случае - false
     */
    private boolean checkProductAvailability(Long productId, int quantity) {
        return productService.checkProductReservation(productId, quantity);
    }

    /**
     * Вычисляет общую стоимость товара.
     *
     * @param productId ID товара
     * @param quantity  количество товара
     * @return общая стоимость товара
     */
    private double calculateTotalPrice(Long productId, int quantity) {
        return productService.getProductPrice(productId) * quantity;
    }

    // Создаём нового клиента
    public Client createClient(String name, double walletAmount) {
        Client client = new Client();
        client.setName(name);
        client.setRegistrationDate(new Date());
        client.setWalletAmount(walletAmount);
        return clientRepository.save(client);
    }
}