package ru.sakaev.demo.controllers;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

@Controller
public class RandomControllers {
    @GetMapping("/random")
    public  String getRandomNumber(Model model) {
        model.addAttribute("name", new Random().nextInt(101));
        return "random";
    }
}