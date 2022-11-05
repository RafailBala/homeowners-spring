package com.example.homeownersspring.dto;

import com.example.homeownersspring.model.CounterType;
import lombok.Data;

@Data
public class CounterTypeDto {
    private Long id;
    private String name;

    public CounterType toCounterType(){
        CounterType counterType=new CounterType();
        counterType.setId(id);
        counterType.setName(name);
        return counterType;
    }
    public CounterTypeDto fromCounterType(CounterType counterType){
        CounterTypeDto counterTypeDto=new CounterTypeDto();
        counterTypeDto.setId(counterType.getId());
        counterTypeDto.setName(counterType.getName());
        return counterTypeDto;
    }
}
