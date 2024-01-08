package com.marijana.library1223.services;

import com.marijana.library1223.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    //PasswordEncoder passwordEncoder; - add after adding dependencies

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }


}
