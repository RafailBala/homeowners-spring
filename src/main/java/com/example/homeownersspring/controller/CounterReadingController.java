package com.example.homeownersspring.controller;

import com.example.homeownersspring.dto.CounterReadingDtoForHistory;
import com.example.homeownersspring.service.impl.CounterReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/app/history/")
public class CounterReadingController {

    @Autowired
    private CounterReadingService counterReadingService;

    @GetMapping(value = "getAllCounters")
    public List<CounterReadingDtoForHistory> getAll() {
        return counterReadingService.getAll();
    }
}