package com.marijana.library1223.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
@Data
@Entity
@Table(name="accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name_student", length = 64)
    private String firstNameStudent;
    @Column(name = "last_name_student", length = 64)
    private String lastNameStudent;
    @Column(name = "dob")
    private LocalDate dob;
    @Column(name = "student_class")
    private String studentClass;
    @Column(name = "name_of_teacher")
    private String nameOfTeacher;

    //Relations.............
    //OWNER - this is where we configure the relationship
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "account_reservations",
            joinColumns = {@JoinColumn(name = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "reservation_id")}
    )
   // @JoinColumn(name = "account_reservations_id")
    private List<Reservation> reservations = new ArrayList<>();

    //TARGET - relation with Borrowal
    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private List<Borrowal> borrowals = new ArrayList<>();

}