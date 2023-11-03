package com.kusaikina.lab3.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "fleas")
public class Flea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FleaId")
    private long id;
    @Column(name="Name")
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CatId")
    private Cat cat;

    public Flea(String name, Cat cat) {
        this.name = name;
        this.cat = cat;
    }

    public Flea() {}

}