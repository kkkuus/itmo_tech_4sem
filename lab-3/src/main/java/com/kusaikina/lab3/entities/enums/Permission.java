package com.kusaikina.lab3.entities.enums;

import lombok.Getter;

@Getter
public enum Permission {
    READ("READ"),
    WRITE("WRITE");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }
}
