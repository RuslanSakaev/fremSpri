package ru.sakaev.microServReserv.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import ru.sakaev.microServReserv.model.Product;

@FeignClient(name = "backend-app", url = "${backend.app.url}")
public interface BackendAppClient {

    @GetMapping("/api/products/{id}")
    Product getProductById(@PathVariable("id") Long id);

    @PutMapping("/api/products/{id}")
    void updateProduct(@PathVariable("id") Long id, @RequestBody Product product);

    @PutMapping("/api/products/release/{id}")
    void releaseProductFromReservation(@PathVariable("id") Long id, @RequestParam("quantity") int quantityToRelease);
}
