package com.example.homeownersspring.service;


import com.example.homeownersspring.dto.UserDto;
import com.example.homeownersspring.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    User register(UserDto user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);
}
