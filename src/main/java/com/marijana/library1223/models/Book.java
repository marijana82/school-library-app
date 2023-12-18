package com.marijana.library1223.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marijana.library1223.enums.TopicEnum;
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

    //Relations..............
    //OWNER - here we specify the join-table/configure the relationship
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    /*@JoinTable(
            name = "book_reservations",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "reservation_id")}
    )*/
    @JoinColumn(name = "book_reservation_id")
    private Reservation reservation;


    //Relations...........
    //TARGET
    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<BookCopy> bookCopyList = new ArrayList<>();

    //TARGET - relation with AuthorBook
    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<AuthorBook> authorBooks = new ArrayList<>();


}

