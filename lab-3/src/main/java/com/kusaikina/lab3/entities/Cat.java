package com.kusaikina.lab3.entities;


import com.kusaikina.lab3.entities.enums.CatsColor;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "cats")
@NoArgsConstructor
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CatId")
    private long id;
    @Column(name="Name")
    private String name;
    @Column(name="Date")
    private Date date;
    @Column(name="Breed")
    private String breed;
    @Column(name="Color")
    @Enumerated(EnumType.STRING)
    private CatsColor color;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OwnerId")
    private Owner owner;
    @Column(name = "TailLength")
    private Integer tailLength;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cat")
    private List<Flea> fleas;

    public Cat (String name, Date date, String breed, CatsColor color, Owner owner, Integer tailLength) {
        this.name = name;
        this.date = date;
        this.breed = breed;
        this.color = color;
        this.owner = owner;
        this.tailLength = tailLength;
    }
}
