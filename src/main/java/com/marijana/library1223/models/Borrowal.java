package com.marijana.library1223.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="borrowals")
public class Borrowal {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name="date_of_borrowal")
    private LocalDate dateOfBorrowal;
    @Column(name="due_date")
    private LocalDate dueDate;
    @Column(name="book_title")
    private String bookTitle;
    @Column(name="number_of_books_borrowed")
    private Integer numberOfBooksBorrowed;

    //status
    //private Status status; (enum)

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

    public Integer getNumberOfBooksBorrowed() {
        return numberOfBooksBorrowed;
    }

    public void setNumberOfBooksBorrowed(Integer numberOfBooksBorrowed) {
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
