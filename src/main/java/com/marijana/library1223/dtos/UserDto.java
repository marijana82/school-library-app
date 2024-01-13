package com.marijana.library1223.dtos;

import com.marijana.library1223.models.Authority;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class UserDto {
    @NotBlank
    public String username;
    public String password;
    public Boolean enabled;
    public String apikey;
    public String email;
    private String firstname;
    private String lastname;
    public Set<Authority> authorities;

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

}
