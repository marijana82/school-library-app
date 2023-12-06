package com.marijana.library1223.models;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name="book_copies")
public class BookCopy {

    @Id
    @GeneratedValue
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


    //getters&setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Integer getTotalWordCount() {
        return totalWordCount;
    }

    public void setTotalWordCount(Integer totalWordCount) {
        this.totalWordCount = totalWordCount;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isInWrittenForm() {
        return inWrittenForm;
    }

    public void setInWrittenForm(boolean inWrittenForm) {
        this.inWrittenForm = inWrittenForm;
    }

    public boolean isAudioBook() {
        return audioBook;
    }

    public void setAudioBook(boolean audioBook) {
        this.audioBook = audioBook;
    }

    public boolean isDyslexiaFriendly() {
        return dyslexiaFriendly;
    }

    public void setDyslexiaFriendly(boolean dyslexiaFriendly) {
        this.dyslexiaFriendly = dyslexiaFriendly;
    }

    public LocalDate getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(LocalDate yearPublished) {
        this.yearPublished = yearPublished;
    }
}



