package com.example.homeownersspring.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;


@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private int  house;

    @Column(name = "building")
    private String building;

    @Column(name = "apartment")
    private int apartment;

    @Column(name = "password")
    private String password;

    @ElementCollection(targetClass =Role.class, fetch = FetchType.EAGER )
    @CollectionTable(name="user_role",joinColumns =@JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

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

    public User() {

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

