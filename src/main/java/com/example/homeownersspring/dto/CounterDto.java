package com.example.homeownersspring.dto;

import com.example.homeownersspring.model.Counter;
import com.example.homeownersspring.model.CounterType;
import com.example.homeownersspring.service.impl.CounterTypeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class CounterDto {

    private Long id;
    private String number;
    private int counterReading;
    private long id_User;
    private String counterTypeName;
    private int newCounterReading;
    @Autowired
    private final CounterTypeService counterTypeService;

    public CounterDto(CounterTypeService counterTypeService) {
        this.counterTypeService = counterTypeService;
    }

    public CounterDto fromCounter(Counter counter){
        CounterDto counterDto=new CounterDto(counterTypeService);
        counterDto.setId(counter.getId());
        counterDto.setNumber(counter.getNumber());
        counterDto.setCounterReading(Integer.parseInt(String.valueOf(counter.getCounterReadings().get(counter.getCounterReadings().size() - 1).getValue())));
        counterDto.setId_User(counter.getId());
        CounterType counterType=counterTypeService.findById(counter.getId_CounterType().getId());
        counterDto.setCounterTypeName(counterType.getName());
        return counterDto;
    }

}
