package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.UserDto;
import com.marijana.library1223.exceptions.BadRequestException;
import com.marijana.library1223.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //------users
    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                stringBuilder.append(fieldError.getDefaultMessage());
                stringBuilder.append("\n");
            }
            return ResponseEntity.badRequest().body(stringBuilder.toString());
        } else {

            String newUsername = userService.createNewUser(userDto);
            userService.addAuthority(newUsername, "ROLE_USER"); //userDto.getAuthority()

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{username}")
                    .buildAndExpand(newUsername)
                    .toUri();

            return ResponseEntity.created(location).build();
        }
    }

    @GetMapping(value = "/{username}")
    ResponseEntity<UserDto> getUser(@PathVariable("username") String username) {
        UserDto optionalUser = userService.getUserByUsername(username);
        return ResponseEntity.ok().body(optionalUser);
    }

    @GetMapping
    ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> userDtos = userService.getAllUsers();
        return ResponseEntity.ok().body(userDtos);
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<UserDto> updateOneUser(@PathVariable("username") String username, @RequestBody UserDto userDto) {
        userService.updateUserPassword(username, userDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/delete/{username}")
    public ResponseEntity<Object> deleteOneUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    //-----authorities

    //add authority to user
    @PostMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            userService.addAuthority(username, authorityName);
            return ResponseEntity.noContent().build();
        }
        catch (Exception exception) {
            throw new BadRequestException();
        }
    }

    //get all user authorities(roles)
    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getUserAuthority(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getAuthority(username));
    }

    //delete user authorities
    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        userService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }

    
}
