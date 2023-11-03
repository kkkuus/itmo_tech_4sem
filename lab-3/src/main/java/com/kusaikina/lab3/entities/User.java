package com.kusaikina.lab3.entities;

import com.kusaikina.lab3.entities.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Password")
    private String password;

    @Column(name = "Role")
    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "OwnerId")
    private Owner owner;


    public User(String name, String password, Role role, Owner owner) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.owner = owner;
    }
}
