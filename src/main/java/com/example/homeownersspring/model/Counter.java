package com.example.homeownersspring.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="counter")
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "number")
    private String number;

    @NotNull
    @Column(name = "counter_reading")
    private int counterReading;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_User", referencedColumnName = "id")
    private User id_User;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_CounterType", referencedColumnName = "id")
    private CounterType id_CounterType;

    @OneToMany(mappedBy="id_Indication", fetch= FetchType.EAGER)
    private List<Indication> indications;

    public Counter(){
    }

    public Counter(String number, int counterReading, User id_User, CounterType id_CounterType) {
        this.number = number;
        this.counterReading = counterReading;
        this.id_User = id_User;
        this.id_CounterType = id_CounterType;
    }
}
