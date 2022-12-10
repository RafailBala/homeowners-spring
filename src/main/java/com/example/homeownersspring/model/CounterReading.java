package com.example.homeownersspring.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name="counter_reading")
public class CounterReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date")
    private Timestamp date;

    @NotNull
    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "counter_id")
    private Counter counter;

    public CounterReading (Timestamp date, String value) {
        this.date = date;
        this.value = value;
    }

    public CounterReading() {
    }
}
