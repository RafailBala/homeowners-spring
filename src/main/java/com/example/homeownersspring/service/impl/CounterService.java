package com.example.homeownersspring.service.impl;

import com.example.homeownersspring.model.Counter;
import com.example.homeownersspring.model.CounterType;
import com.example.homeownersspring.model.User;
import com.example.homeownersspring.repo.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounterService {

    @Autowired
    private CounterRepository counterRepository;

    public List<Counter> getAll() {
        List<Counter> result = counterRepository.findAll();
        return result;
    }


    public Counter findById(Long id) {
        Counter result = counterRepository.findById(id).orElse(null);
        if (result == null) {
            return null;
        }
        return result;
    }

    public void add(Counter counter){
        counterRepository.save(counter);
    }
}
