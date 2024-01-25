package com.marijana.library1223.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name="sidenote")
    private String sidenote;

    //TARGET..........
    @OneToOne(
            mappedBy = "reservation",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Borrowal borrowal;


    //OWNER..........
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinColumn(name = "account_id")
    private Account account;

    //OWNER..........
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinColumn(name = "book_id")
    private Book book;

}
