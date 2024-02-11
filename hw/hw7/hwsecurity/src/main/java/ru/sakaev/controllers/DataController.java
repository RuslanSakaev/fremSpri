package ru.sakaev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/public-data")
    public String publicData() {
        return "public";
    }

    @GetMapping("/private-data")
    public String privateData() {
        return "private";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}