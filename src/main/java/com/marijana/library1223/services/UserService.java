package com.marijana.library1223.services;

import com.marijana.library1223.dtos.UserDto;
import com.marijana.library1223.exceptions.UsernameNotFoundException;
import com.marijana.library1223.models.Authority;
import com.marijana.library1223.models.User;
import com.marijana.library1223.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //helper method - checks if user exists
    public boolean usernameExists(String username) {
        return userRepository.existsById(username);
    }

    //create user
    public String createNewUser(UserDto userDto) {
         User newUser = userRepository.save(transferUserDtoToUser(userDto));
         return newUser.getUsername();
    }

    //get all users
    public List<UserDto> getAllUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(transferUserToUserDto(user));
        }
        return collection;
    }


    //get one user
    public UserDto getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findById(username);
        if(usernameExists(String.valueOf(optionalUser.get()))) {
            User requestedUser = optionalUser.get();
            return transferUserToUserDto(requestedUser);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }


    //delete user
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }


    //update user
    public void updateUserPassword(String username, UserDto newUser) {
        if(!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
    }

    //----authorities

    //get authority
    public List<Authority> getAuthority(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        UserDto userDto = transferUserToUserDto(user);
        return userDto.getAuthority();
    }

    //add authority
    public void addAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    //delete authority
    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User userFound = userRepository.findById(username).get();
        Authority authorityToRemove = userFound.getAuthority().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        userFound.removeAuthority(authorityToRemove);
        userRepository.save(userFound);
    }



    //helper methods
    public static UserDto transferUserToUserDto(User user){
        UserDto dto = new UserDto();
        dto.username = user.getUsername();
        dto.password = user.getPassword();
        //TODO:is this the problem???
        dto.setAuthority(user.getAuthority());
        return dto;
    }

    public User transferUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }






}
