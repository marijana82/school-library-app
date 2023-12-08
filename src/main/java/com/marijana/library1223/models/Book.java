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


    //constructors
    public Book() {

    }

    public Book(int isbn, String bookTitle, String nameAuthor, String nameIllustrator, int suitableAge, ReadingBook readingBook, InformationBook informationBook) {
        this.isbn = isbn;
        this.bookTitle = bookTitle;
        this.nameAuthor = nameAuthor;
        this.nameIllustrator = nameIllustrator;
        this.suitableAge = suitableAge;
        this.readingBook = readingBook;
        this.informationBook = informationBook;
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
}

