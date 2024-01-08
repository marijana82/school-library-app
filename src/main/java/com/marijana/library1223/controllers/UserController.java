package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.UserDto;
import com.marijana.library1223.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    //get one user
    @GetMapping(value = "/{username}")
    ResponseEntity<UserDto> getUser(@PathVariable("username") String username) {
        UserDto optionalUser = userService.getUser(username);
        return ResponseEntity.ok().body(optionalUser);
    }

    //get all users
    @GetMapping
    ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> userDtos = userService.getUsers();
        return ResponseEntity.ok().body(userDtos);
    }






    
}
