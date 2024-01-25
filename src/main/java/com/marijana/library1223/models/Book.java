package com.marijana.library1223.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marijana.library1223.fileUploadResponse.FileUploadResponse;
import jakarta.persistence.*;
import lombok.Data;

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
    private Integer isbn;
    @Column(name = "book_title")
    private String bookTitle;
    @Column(name = "name_author")
    private String nameAuthor;
    @Column(name = "name_illustrator")
    private String nameIllustrator;
    @Column(name = "suitable_age")
    private Integer suitableAge;
    //here add enum genre


    @OneToOne
    FileDocument bookPhoto;

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

    public Book(Long id, Integer isbn, String bookTitle, String nameAuthor, String nameIllustrator, Integer suitableAge) {
    }


}

