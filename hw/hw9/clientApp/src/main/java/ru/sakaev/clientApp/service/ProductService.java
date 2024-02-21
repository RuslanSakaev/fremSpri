package com.example.clientapp.service;

import com.example.clientapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {
    private final String backendUrl = "http://localhost:8081/api/products";

    @Autowired
    private RestTemplate restTemplate;

    public Product[] getAllProducts() {
        return restTemplate.getForObject(backendUrl, Product[].class);
    }

    // Другие методы для взаимодействия с Backend API
}
