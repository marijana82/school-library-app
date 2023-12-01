package com.marijana.library1223.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public class BorrowalDto {
    private Long id;

    @NotBlank(message = "Please provide student's first name.")
    private String firstNameStudent;
    @NotBlank(message = "Please provide student's last name.")
    private String lastNameStudent;
    @NotBlank(message = "Please provide student's class name.")
    private String studentClass;
    @NotBlank(message = "Please provide the name of the book.")
    private String bookTitle;
    private String initialsAuthor;
    @NotBlank(message = "Please provide author's last name.")
    private String lastNameAuthor;
    @Max(5)
    private int numberOfBooksBorrowed;


    //getters&setters
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

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
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
