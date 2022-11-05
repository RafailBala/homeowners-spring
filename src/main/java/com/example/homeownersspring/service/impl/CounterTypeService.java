package com.example.homeownersspring.service.impl;

import com.example.homeownersspring.model.CounterType;
import com.example.homeownersspring.model.User;
import com.example.homeownersspring.repo.CounterTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounterTypeService  {

    @Autowired
    private CounterTypeRepository counterTypeRepository;

    public List<CounterType> getAll() {
        List<CounterType> result = counterTypeRepository.findAll();
        return result;
    }


    public CounterType findById(Long id) {
        CounterType result = counterTypeRepository.findById(id).orElse(null);
        if (result == null) {
            return null;
        }
        return result;
    }

    public void add(String name){
        CounterType counterType=new CounterType(name);
        counterTypeRepository.save(counterType);
    }
}
