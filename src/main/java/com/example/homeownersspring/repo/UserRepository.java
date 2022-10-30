package com.example.homeownersspring.repo;

import com.example.homeownersspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String user);
}