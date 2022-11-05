package com.example.homeownersspring.repo;

import com.example.homeownersspring.model.CounterType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterTypeRepository extends JpaRepository<CounterType,Long> {
}
