package com.kusaikina.lab3.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kusaikina.lab3.entities.Cat;
import com.kusaikina.lab3.entities.User;
import com.kusaikina.lab3.entities.enums.CatsColor;
import lombok.Getter;

import java.util.Date;
@Getter
public class CatDto {
    private long id;
    private String name;
    private Date date;
    private String breed;
    private CatsColor color;
    private long ownerId;
    private Integer tailLength;
    @JsonIgnore
    private User user;

    public CatDto(Cat cat) {
        this.id = cat.getId();
        this.name = cat.getName();
        this.date = cat.getDate();
        this.breed = cat.getBreed();
        this.color = cat.getColor();
        this.ownerId = cat.getOwner().getId();
        this.tailLength = cat.getTailLength();
        this.user = cat.getOwner().getUser();
    }
}
