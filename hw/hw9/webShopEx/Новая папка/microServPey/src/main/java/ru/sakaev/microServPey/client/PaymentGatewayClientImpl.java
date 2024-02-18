package ru.sakaev.microServPey.client;

import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayClientImpl extends PaymentGatewayClient {

    @Override
    public PaymentResult processPayment(Payment payment) {
        // Логика обработки платежа через платежный шлюз
        // Например, вызов внешнего API, базы данных и т.д.
        // Здесь просто возвращаем успешный результат в качестве примера
        return new PaymentResult(true);
    }
}
