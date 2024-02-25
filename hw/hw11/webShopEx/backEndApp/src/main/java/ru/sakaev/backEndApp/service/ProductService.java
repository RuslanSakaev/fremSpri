package ru.sakaev.backEndApp.service; // Объявление пакета для класса ProductService

import org.slf4j.Logger; // Импорт интерфейса Logger из SLF4J
import org.slf4j.LoggerFactory; // Импорт фабрики LoggerFactory из SLF4J
import org.springframework.beans.BeanUtils; // Импорт класса BeanUtils из Spring Framework для копирования свойств объектов
import org.springframework.beans.factory.annotation.Autowired; // Импорт аннотации для автоматического внедрения зависимостей
import org.springframework.stereotype.Service; // Импорт аннотации Service из Spring Framework
import org.springframework.transaction.annotation.Transactional; // Импорт аннотации Transactional из Spring Framework
import ru.sakaev.backEndApp.model.Product; // Импорт класса Product
import ru.sakaev.backEndApp.repository.ProductRepository; // Импорт интерфейса ProductRepository

import java.util.List; // Импорт класса List

@Service // Аннотация, указывающая, что это класс сервиса
@Transactional(readOnly = true) // Аннотация, определяющая транзакционное поведение для всего сервиса
public class ProductService { // Объявление класса ProductService

    private final ProductRepository productRepository; // Объявление экземпляра ProductRepository
    private final Logger logger = LoggerFactory.getLogger(ProductService.class); // Создание логгера

    @Autowired // Аннотация для автоматического внедрения зависимости
    public ProductService(ProductRepository productRepository) { // Конструктор с параметром для внедрения ProductRepository
        this.productRepository = productRepository; // Инициализация экземпляра ProductRepository
    }

    @Transactional(readOnly = true) // Аннотация, определяющая транзакционное поведение метода
    public List<Product> getAllProducts() { // Метод для получения всех продуктов
        return productRepository.findAll(); // Получение всех продуктов из репозитория
    }

    @Transactional(readOnly = true) // Аннотация, определяющая транзакционное поведение метода
    public Product getProductById(Long id) { // Метод для получения продукта по его ID
        return productRepository.findById(id) // Поиск продукта по ID
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id)); // Выброс исключения, если продукт не найден
    }

    @Transactional // Аннотация, определяющая транзакционное поведение метода
    public Product createProduct(Product product) { // Метод для создания нового продукта
        return productRepository.save(product); // Сохранение нового продукта в репозитории
    }

    @Transactional // Аннотация, определяющая транзакционное поведение метода
    public Product updateProduct(Long id, Product product) { // Метод для обновления существующего продукта
        Product existingProduct = getProductById(id); // Получение существующего продукта по его ID
        if (existingProduct != null) { // Если продукт существует
            BeanUtils.copyProperties(product, existingProduct, "id"); // Копирование свойств из переданного продукта в существующий, исключая поле "id"
            return productRepository.save(existingProduct); // Сохранение обновленного продукта в репозитории
        } else {
            throw new RuntimeException("Product not found with id: " + id); // Выброс исключения, если продукт не найден
        }
    }

    @Transactional // Аннотация, определяющая транзакционное поведение метода
    public void deleteProduct(Long id) { // Метод для удаления продукта по его ID
        productRepository.deleteById(id); // Удаление продукта из репозитория по его ID
    }
}
