package com.example.homeownersspring.dto;

import com.example.homeownersspring.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCounterRequestDto {
    private String street;
    private int house;
    private String building;
    private int apartment;


    public User toUser(){
        User user = new User();
        user.setBuilding(building);
        user.setHouse(house);
        user.setStreet(street);
        user.setApartment(apartment);
        return user;
    }

    public static UserCounterRequestDto fromUser(User user) {
        UserCounterRequestDto userDto = new UserCounterRequestDto();
        userDto.setStreet(user.getStreet());
        userDto.setHouse(user.getHouse());
        userDto.setBuilding(user.getBuilding());
        userDto.setApartment(user.getApartment());
        return userDto;
    }
}

