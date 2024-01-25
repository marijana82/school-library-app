package com.marijana.library1223.services;

import com.marijana.library1223.configuration.PaginationConfiguration;
import com.marijana.library1223.dtos.UserDto;
import com.marijana.library1223.exceptions.PasswordNotValidException;
import com.marijana.library1223.exceptions.UsernameAlreadyExistsException;
import com.marijana.library1223.exceptions.UsernameNotFoundException;
import com.marijana.library1223.models.Authority;
import com.marijana.library1223.models.User;
import com.marijana.library1223.repositories.UserRepository;
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


    public String createNewUser(UserDto userDto) {
        String password = userDto.getPassword();
        String username = userDto.getUsername();

        if(usernameExists(username)) {
            throw new UsernameAlreadyExistsException("This username is already taken. Please create a new one.");
        }

        if(validatePassword(password)) {
                  userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
                  User newUser = userRepository.save(transferUserDtoToUser(userDto));
                  return newUser.getUsername();
             } else {
                throw new PasswordNotValidException("Please edit your password to minimum 5 characters containing at least 1 uppercase/and 1 lowercase/and 1 special character, no whitespaces.");
             }
             }



    public List<UserDto> getAllUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(transferUserToUserDto(user));
        }
        return collection;
    }


    public List<UserDto> getAllUsersByLimitAndOffset(int limit, int offset) {
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> userList = userRepository.findAll();

        int startIndex = PaginationConfiguration.calculateStartIndex(offset, userList.size());
        int endIndex = PaginationConfiguration.calculateEndIndex(offset, limit, userList.size());

        for(int i = startIndex; i < endIndex; i++) {
            userDtoList.add(transferUserToUserDto(userList.get(i)));
        }

        return userDtoList;
    }



    public UserDto getUserByUsername(String username) {
        UserDto userDto;
        Optional<User> optionalUser = userRepository.findById(username);

        if(optionalUser.isPresent()) {
            userDto = transferUserToUserDto(optionalUser.get());

        } else {
            throw new UsernameNotFoundException(username);
        }
        return userDto;
    }



    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }



    public void updateUserPassword(String username, UserDto newUser) {
        if(!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
    }


    public Set<Authority> getAuthority(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        UserDto userDto = transferUserToUserDto(user);
        return userDto.getAuthorities();
    }

    //add authority to user
    public void addAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }


    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User userFound = userRepository.findById(username).get();
        Authority authorityToRemove = userFound.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        userFound.removeAuthority(authorityToRemove);
        userRepository.save(userFound);
    }



    //--------------helper methods---------------------
    public static UserDto transferUserToUserDto(User user){
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEnabled(user.isEnabled());
        dto.setEmail(user.getEmail());
        dto.setAuthorities(user.getAuthorities());
        //dto.setFirstname(user.getFirstName());
        //dto.setLastname(user.getLastName());
        return dto;
    }

    public User transferUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEnabled(userDto.getEnabled());
        user.setEmail(userDto.getEmail());
        //user.setFirstName(userDto.getFirstname());
        //user.setLastName(userDto.getLastname());
        return user;
    }



    public boolean usernameExists(String username) {
        return userRepository.existsById(username);
    }



    public Boolean validatePassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*?=])(?=\\S+$).{6,}$");
    }

    //explanation regex:
/*  ^                 - start-of-string
    (?=.*[0-9])       - at least one digit
    (?=.*[a-z])       - at least one lower case letter
    (?=.*[A-Z])       - at least one upper case letter
    (?=.*[@#$%^&+=])  - at least one special character
    (?=\S+$)          - no whitespace allowed
    .{6,}             - minimum 6 characters
    $                 # end-of-string*/






}
