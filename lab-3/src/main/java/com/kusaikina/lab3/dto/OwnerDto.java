package com.kusaikina.lab3.dto;

import com.kusaikina.lab3.entities.Owner;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class OwnerDto {
    private long id;
    private String name;
    private Date date;
    private List<CatDto> cats;

    public OwnerDto(Owner owner) {
        this.id = owner.getId();
        this.name = owner.getName();
        this.date = owner.getDate();
    }

}