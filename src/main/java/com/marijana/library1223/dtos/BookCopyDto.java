package com.marijana.library1223.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookCopyDto {

    private Long id;
    @NotBlank(message = "Please provide a barcode.")
    private String barcode;
    @NotNull(message = "Must not be left empty.")
    private int numberOfPages;
    @NotNull(message = "Must not be left empty.")
    private int totalWordCount;
    private String format;
    private boolean isInWrittenForm;
    private boolean isAudioBook;
    private boolean isDyslexiaFriendly;
    @NotBlank(message = "Please provide publishing year.")
    private int yearPublished;


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

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getTotalWordCount() {
        return totalWordCount;
    }

    public void setTotalWordCount(int totalWordCount) {
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

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }
}
