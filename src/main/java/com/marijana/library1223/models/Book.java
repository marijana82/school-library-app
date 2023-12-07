package com.marijana.library1223.models;

import jakarta.persistence.*;
import lombok.Data;

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
    @Column(name = "name_illustrator")
    private String nameIllustrator;
    @Column(name = "suitable_age")
    private int suitableAge;
    @Embedded
    private ReadingBook readingBook;

}

