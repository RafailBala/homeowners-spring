package com.example.homeownersspring.dto;

import lombok.Data;

@Data
public class CounterPostDto {
    private Long typeId;
    private String number;
    private int counterReading;
}
