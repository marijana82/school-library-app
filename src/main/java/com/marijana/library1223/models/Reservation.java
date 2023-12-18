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
    //NOT OWNER - target side....................

    @OneToOne(
            mappedBy = "reservation",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Borrowal borrowal;


    //NOT OWNER - target side......................
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "reservations")
    @JsonIgnore
    private List<Account> accounts = new ArrayList<>();

    //OWNER.....................
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinColumn(name = "book_id")
    private Book book;


}
