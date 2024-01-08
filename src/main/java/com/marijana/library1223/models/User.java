package com.marijana.library1223.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")

public class User {
    @Id
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, length = 255)
    private String password;
    @Column(nullable = false)
    private boolean enabled = true;
    @Column
    private String apiKey;
    @Column
    private String email;

    //create one to many relationship

    //---------

    //public Set<Authority> getAuthorities() { return authorities; }
    //    public void addAuthority(Authority authority) {
    //        this.authorities.add(authority);
    //    }
    //    public void removeAuthority(Authority authority) {
    //        this.authorities.remove(authority);
    //    }



}
