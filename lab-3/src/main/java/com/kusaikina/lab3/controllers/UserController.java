package com.kusaikina.lab3.controllers;

import com.kusaikina.lab3.entities.User;
import com.kusaikina.lab3.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{name}")
    @PreAuthorize("hasAuthority('WRITE')")
    public ResponseEntity<Void> getByName(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }
}
