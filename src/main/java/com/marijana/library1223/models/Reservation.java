package com.marijana.library1223.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="reservation_date")
    private LocalDate reservationDate;
    @Column(name="book_title")
    private String bookTitle;
    @Column(name="number_of_books_reserved")
    private int numberOfBooksReserved;
    @Column(name="sidenote")
    private String sidenote;

}
