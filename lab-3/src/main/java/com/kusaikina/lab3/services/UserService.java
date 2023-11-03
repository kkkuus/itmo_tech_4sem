package com.kusaikina.lab3.services;

import com.kusaikina.lab3.dto.UserDto;
import com.kusaikina.lab3.entities.Owner;
import com.kusaikina.lab3.entities.User;
import com.kusaikina.lab3.entities.enums.Role;
import com.kusaikina.lab3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final OwnersService ownersService;
    private final UserRepository userRepository;

    @Autowired
    public UserService(OwnersService ownersService, UserRepository userRepository) {
        this.ownersService = ownersService;
        this.userRepository = userRepository;
    }

    public UserDto createUser(User user) {
        User tempUser = this.userRepository.save(user);
        return new UserDto(tempUser);
    }
}
