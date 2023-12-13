package com.marijana.library1223.dtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AuthorDto {
    //fill in validations
    private Long id;

    private String name;

    private String country;

}
