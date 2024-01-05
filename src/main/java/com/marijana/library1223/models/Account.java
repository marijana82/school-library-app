package com.marijana.library1223.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
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
    //TARGET
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "account")
    @JsonIgnore
    private Reservation reservation;

    //TARGET
    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private List<Borrowal> borrowals = new ArrayList<>();

}