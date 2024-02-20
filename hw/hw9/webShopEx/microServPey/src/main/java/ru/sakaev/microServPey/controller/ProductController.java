package ru.sakaev.microServPey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sakaev.microServPey.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveProduct(@RequestParam double userWalletAmount, @RequestParam double productPrice) {
        boolean success = productService.checkBalanceAndReserveProduct(userWalletAmount, productPrice);
        if (success) {
            return ResponseEntity.ok("Product reserved successfully");
        } else {
            return ResponseEntity.badRequest().body("Not enough balance to reserve product");
        }
    }
}