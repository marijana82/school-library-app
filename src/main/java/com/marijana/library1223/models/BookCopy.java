package com.marijana.library1223.models;

import jakarta.persistence.*;

@Entity
@Table(name="book_copies")
public class BookCopy {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "barcode")
    private String barcode;
    @Column(name = "number_of_pages")
    private Integer numberOfPages;
    @Column(name = "total_word_count")
    private Integer totalWordCount;
    @Column(name = "format")
    private String format;
    @Column(name = "is_in_written_form")
    private boolean isInWrittenForm;
    @Column(name = "is_audio_book")
    private boolean isAudioBook;
    @Column(name = "is_dyslexia_friendly")
    private boolean isDyslexiaFriendly;
    @Column(name = "year_published")
    private Integer yearPublished;


    //getters&setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
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
        return isInWrittenForm;
    }

    public void setInWrittenForm(boolean inWrittenForm) {
        isInWrittenForm = inWrittenForm;
    }

    public boolean isAudioBook() {
        return isAudioBook;
    }

    public void setAudioBook(boolean audioBook) {
        isAudioBook = audioBook;
    }

    public boolean isDyslexiaFriendly() {
        return isDyslexiaFriendly;
    }

    public void setDyslexiaFriendly(boolean dyslexiaFriendly) {
        isDyslexiaFriendly = dyslexiaFriendly;
    }

    public Integer getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
    }
}



