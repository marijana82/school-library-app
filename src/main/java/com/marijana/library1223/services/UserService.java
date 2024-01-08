package com.marijana.library1223.services;

import com.marijana.library1223.dtos.UserDto;
import com.marijana.library1223.exceptions.RecordNotFoundException;
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

    //get all users
    public List<UserDto> getUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(transferUserToUserDto(user));
        }
        return collection;
    }


    //get one user
    public UserDto getUser(String username) {
        UserDto dto;
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()){
            dto = transferUserToUserDto(user.get());
        }else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }


    //user exists
    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }


    //create user
    public String createUser(UserDto userDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setApikey(randomString);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User newUser = userRepository.save(transferUserDtoToUser(userDto));
        return newUser.getUsername();
    }

    //delete user
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    //update user
    public void updateUser(String username, UserDto newUser) {
        if(!userRepository.existsById(username)) throw new RecordNotFoundException();
        User user = userRepository.findById(username).get();
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
    }

    //----authorities

    //get authorities
    public Set<Authority> getAuthorities(String username) {
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
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }





    //helper methods
    public static UserDto transferUserToUserDto(User user){

        UserDto dto = new UserDto();

        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.enabled = user.isEnabled();
        dto.apikey = user.getApiKey();
        dto.email = user.getEmail();
        dto.authorities = user.getAuthorities();

        return dto;
    }

    public User transferUserDtoToUser(UserDto userDto) {

        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEnabled(userDto.getEnabled());
        user.setApiKey(userDto.getApikey());
        user.setEmail(userDto.getEmail());

        return user;
    }






}
