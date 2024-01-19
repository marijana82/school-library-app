package com.marijana.library1223.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Embedded
    private InformationBook informationBook;

    //FileDocument for upload
    @OneToOne
    FileDocument fileDocument;

    //TARGET
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "book"
    )
    @JsonIgnore
    private Reservation reservation;

    //TARGET
    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<BookCopy> bookCopyList = new ArrayList<>();

    //TARGET
    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<ReviewBook> reviewBooks = new ArrayList<>();

    //constructors
    public Book() {}

    public Book(Long id, int isbn, String bookTitle, String nameAuthor, String nameIllustrator, int suitableAge) {
    }

    public Book(Long id, int isbn, String bookTitle, String nameAuthor, String nameIllustrator, int suitableAge, InformationBook informationBook) {
    }

    public Book(Long id, int isbn, String bookTitle, String nameAuthor, String nameIllustrator, int suitableAge, InformationBook informationBook, ReadingBook readingBook) {
    }



}

