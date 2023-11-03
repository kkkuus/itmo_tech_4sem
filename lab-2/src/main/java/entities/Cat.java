package entities;


import entities.enums.CatsColor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@Table(name = "cats")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OwnerId")
    private Owner owner;
    @Transient
    private long ownerId;

    public Cat (String name, Date date, String breed, CatsColor color, Owner owner) {
        this.name = name;
        this.date = date;
        this.breed = breed;
        this.color = color;
        this.owner = owner;
        this.ownerId = owner.getId();
    }

    public Cat() {}
}

