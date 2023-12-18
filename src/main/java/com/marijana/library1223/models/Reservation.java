package com.marijana.library1223.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    //Relations..............
    //TODO: CHANGE TARGET-OWNER BETWEEN RESERVATION AND BORROWAL OR TOTALLY BREAK THIS RELATIONSHIP
    //TARGET
    @OneToOne(
            mappedBy = "reservation",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Borrowal borrowal;


    //OWNER
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinColumn(name = "account_id")
    private Account account;

    //OWNER
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinColumn(name = "book_id")
    private Book book;


}
