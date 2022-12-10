package com.example.homeownersspring.repo;

import com.example.homeownersspring.model.CounterReading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterReadingRepository extends JpaRepository<CounterReading, Long> {
}
