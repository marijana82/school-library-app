package com.marijana.library1223.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name="copies")
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "barcode")
    private int barcode;
    @Column(name = "number_of_pages")
    private Integer numberOfPages;
    @Column(name = "total_word_count")
    private Integer totalWordCount;
    @Column(name = "format")
    private String format;

    @Column(name = "in_written_form")
    private boolean inWrittenForm;

    @Column(name = "audio_book")
    private boolean audioBook;

    @Column(name = "dyslexia_friendly")
    private boolean dyslexiaFriendly;

    @Column(name = "year_published")
    private LocalDate yearPublished;


    //Relations...........
    //OWNER
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

}



