package com.marijana.library1223.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
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
    //OWNER - configure relationship here.....................
    @OneToOne(cascade = CascadeType.ALL)
    /*@JoinTable(
            name = "borrowal_reservation",
            joinColumns =
                    {@JoinColumn(name = "borrowal_id", referencedColumnName = "id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "reservation_id", referencedColumnName = "id")}
    )*/

    //to specify the foreign key column as "reservation_id" in the borrowals table
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    //OWNER - relation with Account
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;

    //OWNER - relation with BookCopy
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_copy_id")
    private BookCopy bookCopy;


}
