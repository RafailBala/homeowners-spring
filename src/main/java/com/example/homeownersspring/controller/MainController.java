package com.example.homeownersspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MainController {

    @GetMapping("/app")
    public String getStart(Model model) {
        model.addAttribute("title", "Начальная страница");
        return "start";
    }
    @GetMapping("/")
    public String start(Model model) {
        model.addAttribute("title", "Начальная страница");
        return "start";
    }
}


