package com.example.homeownersspring.dto;

import lombok.Data;

import java.util.List;

@Data
public class CounterReadingDto {
    List<ReadingPostDto> readings;
    List<CounterPostDto> counters;
}
