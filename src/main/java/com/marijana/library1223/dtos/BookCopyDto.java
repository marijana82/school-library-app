package com.marijana.library1223.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class BookCopyDto {
    private Long id;
    @Min(4)
    private int barcode;
    @Positive(message= "Number of pages must be a positive number.")
    private Integer numberOfPages;
    @Positive(message= "Number of pages must be a positive number.")
    private Integer totalWordCount;
    private String format;
    @NotNull
    private boolean inWrittenForm;
    @NotNull(message= "Field cannot be null, please provide true or false value.") //or @AssertTrue (null is also a valid input)
    private boolean audioBook;
    @NotNull(message= "Field cannot be null, please provide true or false value.") //or @AssertTrue (null is also a valid input)
    private boolean dyslexiaFriendly;

    @Past
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
