package com.example.homeownersspring.dto;

import com.example.homeownersspring.model.Counter;
import com.example.homeownersspring.model.CounterReading;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CounterReadingDto {
    List<ReadingPostDto> readings;
    List<CounterPostDto> counters;
}
