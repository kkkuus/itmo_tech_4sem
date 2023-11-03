package com.kusaikina.lab3.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "owners")
@NoArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OwnerId")
    private long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "date")
    private Date date;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "owner")
    private List<Cat> cats;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "owner")
    private User user;

    public Owner(String name, Date date) {
        this.name = name;
        this.date = date;
    }
}