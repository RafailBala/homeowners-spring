package com.example.homeownersspring.service;


import com.example.homeownersspring.dto.UserDto;
import com.example.homeownersspring.model.User;

import java.util.List;

public interface UserServiceDao {

    User register(UserDto user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);
}
