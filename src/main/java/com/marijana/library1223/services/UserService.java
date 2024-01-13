package com.marijana.library1223.services;

import com.marijana.library1223.dtos.UserDto;
import com.marijana.library1223.exceptions.PasswordNotValidException;
import com.marijana.library1223.exceptions.UsernameNotFoundException;
import com.marijana.library1223.models.Authority;
import com.marijana.library1223.models.User;
import com.marijana.library1223.repositories.UserRepository;
import com.marijana.library1223.utils.RandomStringGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //helper method - checks if user exists - delete if not used
    public boolean usernameExists(String username) {
        return userRepository.existsById(username);
    }

    //create user
    public String createNewUser(UserDto userDto) {
        String password = userDto.getPassword();
        if(validatePassword(password)) {
            String randomString = RandomStringGenerator.generateAlphaNumeric(20);
            userDto.setApikey(randomString);
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            User newUser = userRepository.save(transferUserDtoToUser(userDto));
            return newUser.getUsername();
        } else {
            throw new PasswordNotValidException("Please edit your password to this minimum: 5 characters, 1 uppercase, 1 lowercase, 1 special character, no whitespaces.");
        }


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
        UserDto userDto = new UserDto();
        Optional<User> optionalUser = userRepository.findById(username);

        if(optionalUser.isPresent()) {
            userDto = transferUserToUserDto(optionalUser.get());
        } else {
            throw new UsernameNotFoundException(username);
        }
        return userDto;
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
    public Set<Authority> getAuthority(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        UserDto userDto = transferUserToUserDto(user);
        return userDto.getAuthorities();
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
        Authority authorityToRemove = userFound.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        userFound.removeAuthority(authorityToRemove);
        userRepository.save(userFound);
    }



    //helper methods
    public static UserDto transferUserToUserDto(User user){
        UserDto dto = new UserDto();
        dto.username = user.getUsername();
        dto.password = user.getPassword();
        //TODO:is this the problem???
        dto.setAuthorities(user.getAuthorities());
        return dto;
    }

    public User transferUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public Boolean validatePassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*?=])(?=\\S+$).{5,}$");
    }
/*  ^                 - start-of-string
    (?=.*[0-9])       - a digit must occur at least once
    (?=.*[a-z])       - a lower case letter must occur at least once
    (?=.*[A-Z])       - an upper case letter must occur at least once
    (?=.*[@#$%^&+=])  - a special character must occur at least once
    (?=\S+$)          - no whitespace allowed in the entire string
    .{5,}             - minimum 5 characters
    $                 # end-of-string*/






}
