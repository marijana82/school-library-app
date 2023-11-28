package com.marijana.library1223.model;

import jakarta.persistence.*;

@Entity
@Table(name="borrowals")
public class Borrowal {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="first_name_student")
    private String firstNameStudent;
    @Column(name="last_name_student")
    private String lastNameStudent;
    @Column(name="book_title")
    private String bookTitle;
    @Column(name="initials_author")
    private String initialsAuthor;
    @Column(name="last_name_author")
    private String lastNameAuthor;
    @Column(name="number_of_books_borrowed")
    private int numberOfBooksBorrowed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstNameStudent() {
        return firstNameStudent;
    }

    public void setFirstNameStudent(String firstNameStudent) {
        this.firstNameStudent = firstNameStudent;
    }

    public String getLastNameStudent() {
        return lastNameStudent;
    }

    public void setLastNameStudent(String lastNameStudent) {
        this.lastNameStudent = lastNameStudent;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getInitialsAuthor() {
        return initialsAuthor;
    }

    public void setInitialsAuthor(String initialsAuthor) {
        this.initialsAuthor = initialsAuthor;
    }

    public String getLastNameAuthor() {
        return lastNameAuthor;
    }

    public void setLastNameAuthor(String lastNameAuthor) {
        this.lastNameAuthor = lastNameAuthor;
    }

    public int getNumberOfBooksBorrowed() {
        return numberOfBooksBorrowed;
    }

    public void setNumberOfBooksBorrowed(int numberOfBooksBorrowed) {
        this.numberOfBooksBorrowed = numberOfBooksBorrowed;
    }
}
