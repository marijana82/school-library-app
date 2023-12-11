package com.marijana.library1223.models;

import com.marijana.library1223.enums.TopicEnum;
import jakarta.persistence.*;
import lombok.Data;

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
    @ManyToOne(
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



    //constructors
    public Book() {

    }

    public Book(int isbn, String bookTitle, String nameAuthor, String nameIllustrator, int suitableAge, ReadingBook readingBook, InformationBook informationBook, Reservation reservation) {
        this.isbn = isbn;
        this.bookTitle = bookTitle;
        this.nameAuthor = nameAuthor;
        this.nameIllustrator = nameIllustrator;
        this.suitableAge = suitableAge;
        this.readingBook = readingBook;
        this.informationBook = informationBook;
        this.reservation = reservation;
    }

    //getters & setters
    public ReadingBook getReadingBook() {
        return readingBook;
    }

    public void setReadingBook(ReadingBook readingBook) {
        this.readingBook = readingBook;
    }

    public InformationBook getInformationBook() {
        return informationBook;
    }

    public void setInformationBook(InformationBook informationBook) {
        this.informationBook = informationBook;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}

