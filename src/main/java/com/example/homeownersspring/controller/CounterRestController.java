package com.example.homeownersspring.controller;

import com.example.homeownersspring.dto.*;
import com.example.homeownersspring.model.Counter;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/app/")
public class CounterRestController {

    private final UserService userService;
    private final CounterTypeService counterTypeService;
    private final CounterService counterService;
    private final CounterTypeDto counterTypeDto;
    private final CounterDto counterDto;

    @Autowired
    public CounterRestController(UserService userService, CounterTypeService counterTypeService, CounterService counterService, CounterTypeDto counterTypeDto, CounterDto counterDto) {
        this.userService = userService;
        this.counterTypeService = counterTypeService;
        this.counterService = counterService;
        this.counterTypeDto = counterTypeDto;
        this.counterDto = counterDto;
    }

    @PostMapping(value = "counter_readings")
    public ResponseEntity postCounter(@RequestBody CounterReadingDto counterReadingDto) {

        List<CounterPostDto> counterPostDtoList = counterReadingDto.getCounters();
        List<ReadingPostDto> readingPostDtoList = counterReadingDto.getReadings();

        for (int i = 0; i < readingPostDtoList.size(); i++) {
            Counter counter = counterService.findById(readingPostDtoList.get(i).getId());
            counter.setCounterReading(readingPostDtoList.get(i).getCounterReading());
            counterService.save(counter);
        }

        for (int i = 0; i < counterPostDtoList.size(); i++) {

            String numb = counterPostDtoList.get(i).getNumber();
            int counterRdng = counterPostDtoList.get(i).getCounterReading();
            CounterType counterType = counterTypeService.findById(counterPostDtoList.get(i).getTypeId());
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findByUsername(auth.getName());

            Counter counter = new Counter(numb, counterRdng, user, counterType);
            counterService.save(counter);
        }

        Map<Object, Object> response = new HashMap<>();
        return ResponseEntity.ok(response);
    }
}
