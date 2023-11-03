package com.kusaikina.lab3.dto;

import com.kusaikina.lab3.entities.User;
import lombok.Data;
import java.util.Date;

@Data
public class UserDto {
    private long id;
    private String name;
    private String role;
    private Date date;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.role = String.valueOf(user.getRole());
        this.date = user.getOwner().getDate();
    }
}
