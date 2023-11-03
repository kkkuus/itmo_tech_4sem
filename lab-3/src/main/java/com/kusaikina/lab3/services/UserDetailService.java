package com.kusaikina.lab3.services;

import com.kusaikina.lab3.dto.MyUserDetails;
import com.kusaikina.lab3.entities.User;
import com.kusaikina.lab3.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    private UserRepository userRepository;
    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not find");
        }
        return MyUserDetails.userDetails(user);
    }
}
