package com.marijana.library1223.dtos;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Objects;

@Data
public class ReviewDto {

    private Long id;

    private String name;

    @Size(min=1, max=600, message = "Review can be between 1 and 600 characters long.")
    private String review;

    //equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewDto reviewDto)) return false;
        return Objects.equals(getId(), reviewDto.getId()) && Objects.equals(getName(), reviewDto.getName()) && Objects.equals(getReview(), reviewDto.getReview());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getReview());
    }




}
