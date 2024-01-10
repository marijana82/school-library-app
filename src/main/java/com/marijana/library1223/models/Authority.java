package com.marijana.library1223.models;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@IdClass(AuthorityKey.class) //combines id in one class, findById works for both username as for authority
@Table(name = "authorities")
public class Authority implements Serializable {
    @Id
    @Column(nullable = false)
    private String username;

    @Id
    @Column(nullable = false)
    private String authority;

    public Authority() {}
    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    //this constructor is important for deserialization (to create Authority object from String value)
    public Authority(String authority) {
        this.authority = authority;
    }


}
