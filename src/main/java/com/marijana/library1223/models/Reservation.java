package com.marijana.library1223.models;

import jakarta.persistence.*;

@Entity
@Table(name="reservations")
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="first_name_student")
    private String firstNameStudent;
    @Column(name="last_name_student")
    private String lastNameStudent;
    @Column(name="student_class")
    private String studentClass;
    @Column(name="book_title")
    private String bookTitle;
    @Column(name="number_of_books_reserved")
    private int numberOfBooksReserved;


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

    public int getNumberOfBooksReserved() {
        return numberOfBooksReserved;
    }

    public void setNumberOfBooksReserved(int numberOfBooksReserved) {
        this.numberOfBooksReserved = numberOfBooksReserved;
    }
}
