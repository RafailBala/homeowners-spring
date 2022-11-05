package com.example.homeownersspring.repo;

import com.example.homeownersspring.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterRepository extends JpaRepository<Counter,Long> {
}
