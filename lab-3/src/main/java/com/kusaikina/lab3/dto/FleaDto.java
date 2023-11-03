package com.kusaikina.lab3.dto;

import com.kusaikina.lab3.entities.Flea;
import lombok.Getter;

@Getter
public class FleaDto {
    private long id;
    private String name;
    private long catId;
    public FleaDto(Flea flea) {
        this.id = flea.getId();
        this.name = flea.getName();
        this.catId = flea.getCat().getId();
    }

}