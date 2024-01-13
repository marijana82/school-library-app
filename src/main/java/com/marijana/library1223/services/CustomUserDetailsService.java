package com.marijana.library1223.services;

import com.marijana.library1223.dtos.UserDto;
import com.marijana.library1223.models.Authority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto userDto = userService.getUserByUsername(username);

        String password = userDto.getPassword();

        List<Authority> authorities = userDto.getAuthority();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        //to use for String:
        //grantedAuthorities.add(new SimpleGrantedAuthority(authority));

        //to use for List:
        for (Authority authority: authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }

        return new org.springframework.security.core.userdetails.User(username, password, grantedAuthorities);
    }

}
