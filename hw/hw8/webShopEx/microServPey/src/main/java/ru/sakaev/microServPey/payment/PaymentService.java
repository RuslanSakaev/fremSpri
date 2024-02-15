package ru.sakaev.microServPey.payment;

import org.springframework.stereotype.Service;
import ru.sakaev.microServPey.client.PaymentGatewayClient;
import ru.sakaev.microServPey.model.PaymentResult;
import ru.sakaev.microServPey.model.PaymentStatus;

@Service
public class PaymentService {

    private final PaymentGatewayClient paymentGatewayClient;

    public PaymentService(PaymentGatewayClient paymentGatewayClient) {
        this.paymentGatewayClient = paymentGatewayClient;
    }

    public ru.sakaev.microServPey.model.Payment processPayment(ru.sakaev.microServPey.model.Payment payment) {
        // Вызываем платежный шлюз и передаем платеж на обработку
        PaymentResult paymentResult = paymentGatewayClient.processPayment(payment);

        // Проверяем результат обработки платежа
        if (paymentResult.isSuccess()) {
            // Если платеж успешно обработан, обновляем статус платежа
            payment.setStatus(PaymentStatus.SUCCESS.toString());

        } else {
            // Если произошла ошибка при обработке платежа, обновляем статус платежа
            // и записываем информацию об ошибке
            payment.setStatus(PaymentStatus.FAILED.toString());
            payment.setErrorDetails(paymentResult.getErrorDetails());
        }

        // Возвращаем обновленный платеж
        return payment;
    }
}
