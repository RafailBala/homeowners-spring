package com.example.homeownersspring.dto;

import com.example.homeownersspring.model.User;
import lombok.Data;


@Data
public class UserDto {
    private Long id;
    private String username;
    private String firstname;
    private String lastName;
    private String patronymic;
    private String street;
    private int house;
    private String building;
    private int apartment;
    private String password;

    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstName(firstname);
        user.setLastName(lastName);
        user.setBuilding(building);
        user.setHouse(house);
        user.setPassword(password);
        user.setStreet(street);
        user.setPatronymic(patronymic);
        user.setApartment(apartment);
        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstname(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPatronymic(user.getPatronymic());
        userDto.setStreet(user.getStreet());
        userDto.setHouse(user.getHouse());
        userDto.setBuilding(user.getBuilding());
        userDto.setApartment(user.getApartment());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
