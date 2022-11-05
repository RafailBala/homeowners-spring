package com.example.homeownersspring.dto;

import com.example.homeownersspring.model.Counter;
import com.example.homeownersspring.model.CounterType;
import com.example.homeownersspring.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CounterDto {

    private Long id;
    private String number;
    private int counterReading;
    private User id_User;
    private CounterType id_CounterType;

    public Counter toCounter(){
        Counter counter=new Counter();
        counter.setId(id);
        counter.setNumber(number);
        counter.setCounterReading(counterReading);
        counter.setId_User(id_User);
        counter.setId_CounterType(id_CounterType);
        return counter;
    }
    public CounterDto fromCounter(Counter counter){
        CounterDto counterDto=new CounterDto();
        counterDto.setId(counter.getId());
        counterDto.setNumber(counter.getNumber());
        counterDto.setCounterReading(counter.getCounterReading());
        counterDto.setId_User(counter.getId_User());
        counterDto.setId_CounterType(counter.getId_CounterType());
        return counterDto;
    }
}
