# Фреймворк Spring (семинары)
## Урок 11. Spring Actuator. Настройка мониторинга с Prometheus и Grafana.
### Задание: По примерам показанным на семинаре:
1) Подключить к своему проекту зависимости actuator, registry-prometheus и micrometer.
2) Установить и подключить к проекту prometheus
3) Установить и подключить Grafana. В Grafana добавить пару точеу контроля( Например: процессоное время приложения и количество запросов)
- Формат сдачи: проект с добавленными зависимостями, файл настройки prometheus и скриншот Grafana с добавленными контрольными точками.
### Задание со звездочкой:
- Проделать, то же самое с многомодульным проектом(добавить под контроль несколько модулей)
- Добавить собственную метрику.

### Решение: 

Запускаем Eureka:
![Eureka](img/1.bmp)
Установка Grafana:
```
Перейдите на страницу загрузки Grafana: https://grafana.com/grafana/download.
Выберите версию для вашей операционной системы (например, Windows, macOS, Linux).
Скачайте и распакуйте архив с Grafana на вашем компьютере.
Установите Grafana:

Следуйте инструкциям для вашей операционной системы. Обычно это просто копирование файлов в нужное место.
Запустите Grafana:

Перейдите в каталог, где вы установили Grafana.
Найдите исполняемый файл Grafana. На Windows это может быть grafana-server.exe, на macOS и Linux — grafana-server.
Запустите Grafana, выполнив соответствующую команду для вашей операционной системы.
Откройте веб-интерфейс Grafana:

Откройте браузер и перейдите по адресу http://localhost:3000 (по умолчанию) или по адресу, который вы указали при установке.
Войдите в Grafana:

По умолчанию имя пользователя и пароль — admin/admin.
Первый вход в Grafana потребует изменения пароля.
Настройте и используйте Grafana:

После входа вы сможете настроить подключение к источникам данных (например, Prometheus) и создать панели для отображения ваших метрик и данных.
Это базовые шаги для установки и запуска Grafana на локальном компьютере. После этого вы сможете настроить и использовать его для визуализации данных и метрик из различных источников, включая Prometheus.
```
![Eureka](img/2.bmp)
Настройки Prometheus:
```xml
# my global config
global:
  scrape_interval: 15s # Интервал сканирования метрик # Set the scrape interval to every 15 seconds. Default is every 1 minute.
  evaluation_interval: 15s # Интервал оценки правил # Evaluate rules every 15 seconds. The default is every 1 minute.
  # scrape_timeout is set to the global default (10s).

# Alertmanager configuration
alerting:
  alertmanagers:
    - static_configs:
        - targets:
          # - alertmanager:9093

# Load rules once and periodically evaluate them according to the global 'evaluation_interval'.
rule_files:
  # - "first_rules.yml"
  # - "second_rules.yml"

# A scrape configuration containing exactly one endpoint to scrape:
# Here it's Prometheus itself.
scrape_configs:
  # The job name is added as a label `job=<job_name>` to any timeseries scraped from this config.
  - job_name: "prometheus"
    # metrics_path defaults to '/metrics'
    # scheme defaults to 'http'.

    static_configs:
      - targets: ["localhost:9090"] # Адрес, по которому запущен Prometheus

  - job_name: 'api-gateway'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: [ 'localhost:8765' ] # Адрес и порт, на котором запущен apiGateway

  - job_name: 'backend-app'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: [ 'localhost:8081' ] # Адрес и порт, на котором запущен backEndApp

  - job_name: 'config-server'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: [ 'localhost:8888' ] # Адрес и порт, на котором запущен configServer

  - job_name: 'eureka-server'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: [ 'localhost:8761' ] # Адрес и порт, на котором запущен eurekaServer

  - job_name: 'microserv-pey'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: [ 'localhost:8082' ] # Адрес и порт, на котором запущен microServPey

  - job_name: 'microserv-reserv'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: [ 'localhost:8083' ] # Адрес и порт, на котором запущен microServReserv
```
![Eureka](img/3.bmp)
Добавим собственную метрику:
- Импортируем классы Micrometer в контроллер:
```java
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
```
- Внедри экземпляр MeterRegistry в контроллер:
```java
private final MeterRegistry meterRegistry;

@Autowired
public ProductController(ProductService productService, MeterRegistry meterRegistry) {
    this.productService = productService;
    this.meterRegistry = meterRegistry;
}
```
- Использую Timer для измерения времени выполнения методов и увеличения счетчика вызовов:
```java
@GetMapping
@Transactional(readOnly = true)
public ResponseEntity<List<Product>> getAllProducts() {
    Timer.Sample sample = Timer.start(meterRegistry); // Начало измерения времени выполнения
    List<Product> products = productService.getAllProducts();
    sample.stop(meterRegistry.timer("product.controller.getAllProducts")); // Остановка измерения и регистрация метрики
    meterRegistry.counter("product.controller.getAllProducts.count").increment(); // Увеличение счетчика вызовов метода
    return new ResponseEntity<>(products, HttpStatus.OK);
}
```
Добавляем новый конструктор ProductController в класс ProductControllerTest:
```java
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @Mock
    private MeterRegistry meterRegistry;

    @BeforeEach
    public void setup() {
        ProductController productController = new ProductController(productService, meterRegistry);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    // Далее идут другие тесты
}
```
