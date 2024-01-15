package com.marijana.library1223.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
@Data
@Entity
@Table(name="borrowals")
public class Borrowal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date_of_borrowal")
    private LocalDate dateOfBorrowal;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "book_title")
    private String bookTitle;
    @Column(name = "number_of_books_borrowed")
    private Integer numberOfBooksBorrowed;

    //Relations...............
    //OWNER
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    //OWNER
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;

    //OWNER
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_copy_id")
    private BookCopy bookCopy;


}
