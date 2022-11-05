package com.example.homeownersspring.model;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "username")
    private String username;
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @NotNull
    @Column(name = "street")
    private String street;
    @NotNull
    @Column(name = "house")
    private int  house;
    @NotNull
    @Column(name = "building")
    private String building;
    @NotNull
    @Column(name = "apartment")
    private int apartment;
    @NotNull
    @Column(name = "password")
    private String password;

    @ElementCollection(targetClass =Role.class, fetch = FetchType.EAGER )
    @CollectionTable(name="user_role",joinColumns =@JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="id_User")
    List<Request> requestList;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="id_User")
    List<Counter> counterList;

    public User() {

    }
    public User(String username, String firstName, String lastName, String patronymic, String street, int house, String building, int apartment, String password, Set<Role> roles) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.street = street;
        this.house = house;
        this.building = building;
        this.apartment = apartment;
        this.password = password;
        this.roles = roles;
    }
    public User(String username, String firstName, String lastName, String street, int house, String building, int apartment, String password, Set<Role> roles) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.house = house;
        this.building = building;
        this.apartment = apartment;
        this.password = password;
        this.roles = roles;
    }

    public User(String username, String firstname, String lastname, String patronymic, String street, int house, String building, String generateRandomPassword) {
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }
}

