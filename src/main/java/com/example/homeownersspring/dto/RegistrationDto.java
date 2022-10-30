package com.example.homeownersspring.dto;
import lombok.Data;

@Data
public class RegistrationDto {
    private String username;
    private String firstname;
    private String lastName;
    private String patronymic;
    private String street;
    private int house;
    private String building;
    private int apartment;
}
