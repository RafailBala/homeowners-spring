package com.example.homeownersspring.controller;

import com.example.homeownersspring.dto.*;
import com.example.homeownersspring.model.Counter;
import com.example.homeownersspring.model.CounterReading;
import com.example.homeownersspring.model.CounterType;
import com.example.homeownersspring.model.User;
import com.example.homeownersspring.service.impl.CounterService;
import com.example.homeownersspring.service.impl.CounterTypeService;
import com.example.homeownersspring.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/app/")
public class CounterRestController {

    private final UserService userService;
    private final CounterTypeService counterTypeService;
    private final CounterService counterService;

    @Autowired
    public CounterRestController(UserService userService, CounterTypeService counterTypeService, CounterService counterService) {
        this.userService = userService;
        this.counterTypeService = counterTypeService;
        this.counterService = counterService;
    }

    @PostMapping(value = "counter_readings")
    public ResponseEntity postCounter(@RequestBody CounterReadingDto counterReadingDto) {
        try {
            List<CounterPostDto> counterPostDtoList = counterReadingDto.getCounters();
            List<ReadingPostDto> readingPostDtoList = counterReadingDto.getReadings();

            readingPostDtoList
                    .forEach(readingPostDto -> {
                        Counter counter = counterService.findById(readingPostDto.getId());
                        List<CounterReading> counterReadings = counter.getCounterReadings();
                        CounterReading counterReading = new CounterReading(new Timestamp(System.currentTimeMillis()), String.valueOf(readingPostDto.getCounterReading()));
                        counterReadings.add(counterReading);
                        counter.setCounterReadings(counterReadings);
                        counterReading.setCounter(counter);
                        counterService.save(counter);
                    });
            if (counterPostDtoList != null) {
                counterPostDtoList.forEach(counterPostDto -> {
                    String numb = counterPostDto.getNumber();
                    int counterRdng = counterPostDto.getCounterReading();
                    CounterReading counterReading = new CounterReading(new Timestamp(System.currentTimeMillis()), String.valueOf(counterRdng));
                    List<CounterReading> counterReadings = Collections.singletonList(counterReading);
                    CounterType counterType = counterTypeService.findById(counterPostDto.getTypeId());
                    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                    User user = userService.findByUsername(auth.getName());
                    Counter counter = new Counter(numb, counterReadings, user, counterType);
                    counterReading.setCounter(counter);
                    counterService.save(counter);
                });
            }
            Map<Object, Object> response = new HashMap<>();
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return (ResponseEntity) ResponseEntity.badRequest();
        }
    }
}
