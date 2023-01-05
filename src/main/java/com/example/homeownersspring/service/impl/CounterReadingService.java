package com.example.homeownersspring.service.impl;

import com.example.homeownersspring.dto.CounterReadingDtoForHistory;
import com.example.homeownersspring.model.Counter;
import com.example.homeownersspring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CounterReadingService {

    @Autowired
    private UserService userService;

    @Autowired
    private CounterTypeService counterTypeService;

    public List<CounterReadingDtoForHistory> getAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        List<Counter> counters = user.getCounterList();
        List<CounterReadingDtoForHistory> counterReadings = new ArrayList<>();
        for (Counter counter : counters) {
            counterReadings.addAll(counter.getCounterReadings().stream()
                    .map(counterReading -> {
                        return CounterReadingDtoForHistory.fromCounterReading(counterTypeService.findById(counter.getId_CounterType().getId()).getName(), counterReading);
                    })
                    .collect(Collectors.toList()));
        }
        return counterReadings;
    }
}
