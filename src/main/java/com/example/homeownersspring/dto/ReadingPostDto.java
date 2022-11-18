package com.example.homeownersspring.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReadingPostDto {
    private Long id;
    private int counterReading;
}
