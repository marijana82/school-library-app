package com.marijana.library1223.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="reservation_details")
public class ReservationDetail {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name="reservation_date")
    private LocalDate reservationDate;
    @Column(name="expiration_date")
    private LocalDate expirationDate;
    @Column(name="number_of_books")
    private int numberOfBooks;
    @Column(name="comment")
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
