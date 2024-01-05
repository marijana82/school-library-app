package com.marijana.library1223.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.Collection;

@Data
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "review")
    private String review;

    //Relations..............
    //NOT OWNER - target side....................
    //nothing in the database
    @OneToMany(mappedBy = "review")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    Collection<ReviewBook> reviewBooks;


}
