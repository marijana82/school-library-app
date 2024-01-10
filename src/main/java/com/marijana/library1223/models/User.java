package com.marijana.library1223.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")

public class User {
    @Id
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, length = 255)
    private String password;

    //the following three fields are not necessary, can be also deleted for now
    //to send confirmation mail when user creates a new account
    /*@Column(nullable = false)
    private boolean enabled = true;

    @Column
    private String apiKey;
    @Column
    private String email;*/

    //create one-to-many relationship
    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            //this is the reason there's no authorities repository, .ALL saves authority together with user
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    //authorities = roles - is the owner of the relationship
    //private Set<Authority> authorities = new HashSet<>();
    private List<Authority> authorities = new ArrayList<>();


    //---------
        //public Set<Authority> getAuthorities() { return authorities; }
        public List<Authority> getAuthorities() { return authorities; }
        public void addAuthority(Authority authority) {
            this.authorities.add(authority);
        }
        public void removeAuthority(Authority authority) {
            this.authorities.remove(authority);
        }



}
