package ru.sakaev.microServPey.client;

public class PaymentGatewayClient {

    // Метод для отправки платежа на обработку платежному шлюзу
    public PaymentResult processPayment(Payment payment) {
        // Здесь должна быть логика отправки платежа на платежный шлюз
        // и получения результата обработки платежа

        // для примера просто возвращаем успешный результат
        return new PaymentResult(true);
    }
}
