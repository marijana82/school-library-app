package com.marijana.library1223.models;

import com.marijana.library1223.enums.BookTypeEnum;
import com.marijana.library1223.interfaces.BookTypeInterface;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "isbn")
    private int isbn;
    @Column(name = "book_title")
    private String bookTitle;
    @Column(name = "name_author")
    private String nameAuthor;
    @Column(name = "suitable_age")
    private int suitableAge;


}

