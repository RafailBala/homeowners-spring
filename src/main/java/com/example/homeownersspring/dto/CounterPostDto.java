package com.example.homeownersspring.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CounterPostDto {
    private Long typeId;
    private String number;
    private int counterReading;
}
