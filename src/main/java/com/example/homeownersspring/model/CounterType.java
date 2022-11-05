package com.example.homeownersspring.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="counter_type")
public class CounterType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy="id_CounterType", fetch= FetchType.EAGER)
    List<Counter> counterList;

    public CounterType() {

    }

    public CounterType(String name) {
        this.name = name;
    }
}
