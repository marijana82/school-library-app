package com.marijana.library1223.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.Collection;
import java.util.Objects;

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
    //TARGET
    @OneToMany(mappedBy = "review")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    Collection<ReviewBook> reviewBooks;


    //equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review review1)) return false;
        return Objects.equals(getId(), review1.getId()) && Objects.equals(getName(), review1.getName()) && Objects.equals(getReview(), review1.getReview()) && Objects.equals(getReviewBooks(), review1.getReviewBooks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getReview(), getReviewBooks());
    }
}
