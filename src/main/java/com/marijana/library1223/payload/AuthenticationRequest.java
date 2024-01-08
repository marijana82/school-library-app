package com.marijana.library1223.payload;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;

    //constructor
    public AuthenticationRequest() {
    }
    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
