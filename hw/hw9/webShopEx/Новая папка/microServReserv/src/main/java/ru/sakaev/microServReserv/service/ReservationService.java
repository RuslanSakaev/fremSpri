package ru.sakaev.microServReserv.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.sakaev.backEndApp.model.Product;
import ru.sakaev.backEndApp.service.ProductService;
import ru.sakaev.microServReserv.model.ReservationRequest;

@Service
public class ReservationService {
    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    private final RestTemplate restTemplate;
    private final String backendAppUrl; // URL микросервиса backEndApp

    @Autowired
    private ProductService productService;

    @Autowired
    public ReservationService(RestTemplate restTemplate, @Value("${backend.app.url}") String backendAppUrl) {
        this.restTemplate = restTemplate;
        this.backendAppUrl = backendAppUrl;
    }

    public boolean reserveProduct(Long productId, int quantity) {
        // Формируем тело запроса для резервирования товара
        ReservationRequest request = new ReservationRequest(productId, quantity);

        try {
            ResponseEntity<Void> response = restTemplate.postForEntity(
                    backendAppUrl + "/reservation/reserve",
                    request,
                    Void.class
            );
            return response.getStatusCode() == HttpStatus.OK;
        } catch (RestClientException ex) {
            // Заменяем printStackTrace() на логгирование
            logger.error("Error occurred while making REST call", ex);
            return false;
        }
    }

    @Transactional
    public void reserveItem(String itemId, int quantity) {
        logger.info("Reserving {} item(s) with ID {}", quantity, itemId);
        // Получение товара из базы данных
        Product product = productService.getProductById(Long.parseLong(itemId));
        if (product != null) {
            // Проверка наличия достаточного количества товара для резервирования
            if (product.getQuantity() >= quantity) {
                // Уменьшение количества доступного товара
                product.setQuantity(product.getQuantity() - quantity);
                // Сохранение обновленной информации о товаре в базу данных
                productService.createProduct(product);
                logger.info("Reservation successful.");
            } else {
                logger.info("Not enough items available for reservation.");
            }
        } else {
            logger.info("Product not found.");
        }
    }

    @Transactional
    public boolean cancelReservation(String itemId, int quantity) {
        logger.info("Canceling reservation of {} item(s) with ID {}", quantity, itemId);
        // Получение товара из базы данных
        Product product = productService.getProductById(Long.parseLong(itemId));
        if (product != null) {
            // Увеличение количества доступного товара
            product.setQuantity(product.getQuantity() + quantity);
            // Сохранение обновленной информации о товаре в базу данных
            productService.createProduct(product);
            logger.info("Reservation canceled.");
            return true;
        } else {
            logger.info("Product not found.");
            return false;
        }
    }
}
