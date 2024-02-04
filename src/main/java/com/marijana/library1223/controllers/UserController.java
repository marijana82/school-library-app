package com.marijana.library1223.controllers;

import com.marijana.library1223.helpers.HandleBindingErrors;
import com.marijana.library1223.helpers.PaginationConfiguration;
import com.marijana.library1223.dtos.UserDto;
import com.marijana.library1223.exceptions.AccessDeniedException;
import com.marijana.library1223.exceptions.BadRequestException;
import com.marijana.library1223.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {

            return HandleBindingErrors.handleBindingErrors(bindingResult);

        } else {

            String newUsername = userService.createNewUser(userDto);

            userService.addAuthority(newUsername, "ROLE_STUDENT");

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{username}")
                    .buildAndExpand(newUsername)
                    .toUri();

            return ResponseEntity.created(location).build();
        }
    }


    @GetMapping(value = "/{username}")
    ResponseEntity<UserDto> getUser(
            @PathVariable("username") String username,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (username.equals(userDetails.getUsername())) {
            UserDto optionalUser = userService.getUserByUsername(username);
            return ResponseEntity.ok().body(optionalUser);

        } else {
            throw new AccessDeniedException("You are not authorized to access this user");
        }
    }


    @GetMapping
    ResponseEntity<List<UserDto>> getUsers(
            @RequestParam(value = "limit", required = false) Optional<Integer> limit,
            @RequestParam(value = "offset", required = false) Optional<Integer> offset){

        List<UserDto> userDtoList;

        int limitValue = limit.orElse(PaginationConfiguration.DEFAULT_LIMIT);
        int offsetValue = offset.orElse(PaginationConfiguration.DEFAULT_OFFSET);

        if(limit.isPresent() && offset.isPresent()) {
            userDtoList = userService.getAllUsersByLimitAndOffset(limitValue, offsetValue);

        } else {
            userDtoList = userService.getAllUsers();
        }
        return ResponseEntity.ok().body(userDtoList);
    }


    @PutMapping("/update/{username}")
    public ResponseEntity<UserDto> updateOneUser(
            @PathVariable("username") String username,
            @RequestBody UserDto userDto,
            @AuthenticationPrincipal UserDetails userDetails) {

        if(username.equals(userDetails.getUsername())) {

            userService.updateUserPassword(username, userDto);
            return ResponseEntity.noContent().build();

        } else {

            throw new AccessDeniedException("You are not authorized to update this user");

        }

    }


    @DeleteMapping(value = "/delete/{username}" )
    public String deleteUser(
            @PathVariable("username") String username,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (username.equals(userDetails.getUsername())) {

            userService.deleteUser(username);

            return "User " + userDetails.getUsername() + " has been deleted: " + username;

        } else {
            throw new AccessDeniedException("You are not authorized to delete this user");
        }

    }



    //-----authorities

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


    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getUserAuthority(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getAuthority(username));
    }


    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        userService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }

    
}
