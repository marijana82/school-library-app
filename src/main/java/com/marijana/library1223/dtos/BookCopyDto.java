package com.marijana.library1223.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class BookCopyDto {

    private Long id;
    @NotBlank(message = "Please provide a barcode.")
    private String barcode;
    @Positive(message= "Number of pages must be a positive number.")
    private Integer numberOfPages;
    @Positive(message= "Number of pages must be a positive number.")
    private Integer totalWordCount;
    private String format;
    @NotNull(message= "Field cannot be null, please provide true or false value.")
    private boolean isInWrittenForm;
    @NotNull(message= "Field cannot be null, please provide true or false value.") //or @AssertTrue (null is also a valid input)
    private boolean isAudioBook;
    @NotNull(message= "Field cannot be null, please provide true or false value.") //or @AssertTrue (null is also a valid input)
    private boolean isDyslexiaFriendly;
    @NotBlank(message = "Please provide publishing year.")
    private LocalDate yearPublished;


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

    public LocalDate getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(LocalDate yearPublished) {
        this.yearPublished = yearPublished;
    }
}
