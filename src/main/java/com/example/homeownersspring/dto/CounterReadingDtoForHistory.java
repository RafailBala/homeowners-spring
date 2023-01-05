package com.example.homeownersspring.dto;

import com.example.homeownersspring.model.CounterReading;
import com.example.homeownersspring.model.CounterType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
public class CounterReadingDtoForHistory {

    private Timestamp date;
    private String value;
    private String counterType;

    public static CounterReadingDtoForHistory fromCounterReading(String counterTypeName, CounterReading counterReading) {
        return new CounterReadingDtoForHistory(counterReading.getDate(), counterReading.getValue(), counterTypeName);
    }
}
