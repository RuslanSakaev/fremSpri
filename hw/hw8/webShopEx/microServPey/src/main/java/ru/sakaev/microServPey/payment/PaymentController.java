package ru.sakaev.microServPey.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sakaev.microServPey.model.Payment;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Метод для обработки запроса на оплату
    @PostMapping("/payment")
    public Payment processPayment(@RequestBody Payment payment) {
        return paymentService.processPayment(payment);
    }
}