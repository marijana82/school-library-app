package com.marijana.library1223.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class BorrowalDto {
    private Long id;
    @FutureOrPresent
    private LocalDate dateOfBorrowal;
    @FutureOrPresent
    private LocalDate dueDate;
    @NotBlank(message="Please provide book title.")
    private String bookTitle;
    @Max(value=3)
    private Integer numberOfBooksBorrowed;


    //getters&setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getNumberOfBooksBorrowed() {
        return numberOfBooksBorrowed;
    }

    public void setNumberOfBooksBorrowed(int numberOfBooksBorrowed) {
        this.numberOfBooksBorrowed = numberOfBooksBorrowed;
    }

    public LocalDate getDateOfBorrowal() {
        return dateOfBorrowal;
    }

    public void setDateOfBorrowal(LocalDate dateOfBorrowal) {
        this.dateOfBorrowal = dateOfBorrowal;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
