package ru.sakaev.microServPey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sakaev.microServPey.model.Payment;
import ru.sakaev.microServPey.service.PaymentService;
import ru.sakaev.microServReserv.model.Product;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveProducts(@RequestBody Payment payment) {
        List<Product> reservedProducts = payment.getProducts();
        paymentService.reserveProducts(reservedProducts);
        return new ResponseEntity<>("Products reserved successfully", HttpStatus.OK);
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmPayment(@RequestParam Long userId, @RequestParam double totalPrice) {
        paymentService.confirmPayment(userId, totalPrice);
        return new ResponseEntity<>("Payment confirmed successfully", HttpStatus.OK);
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelPayment(@RequestParam Long userId, @RequestParam double totalReservedPrice) {
        paymentService.cancelPayment(userId, totalReservedPrice);
        return new ResponseEntity<>("Payment canceled successfully", HttpStatus.OK);
    }
}
