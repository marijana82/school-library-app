package com.marijana.library1223.dtos;

import com.marijana.library1223.models.Authority;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {

    public String username;
    public String password;
    public Boolean enabled;
    public String email;
    public Set<Authority> authorities;

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

}
