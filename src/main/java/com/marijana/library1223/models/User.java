package com.marijana.library1223.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    private String username;
    private String password;
    private boolean enabled = true;
    private String apiKey;
    private String email;




}
