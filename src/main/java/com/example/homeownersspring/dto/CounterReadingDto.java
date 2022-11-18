package com.example.homeownersspring.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CounterReadingDto {
    List<ReadingPostDto> readings;
    List<CounterPostDto> counters;
}
