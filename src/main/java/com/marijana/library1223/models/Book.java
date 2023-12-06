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
    @Column(name = "suitable_age")
    private int suitableAge;
    @Column(name = "number_of_copies")
    private int numberOfCopies;

    //private PictureBook pictureBook;
    //private ReadingBook readingBook;
    //private InformationBook informationBook;
}

