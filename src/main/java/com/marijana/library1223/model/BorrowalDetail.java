package com.marijana.library1223.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name="borrowal_details")
public class BorrowalDetail {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate borrowalDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    //private Status status (take from enumeration);

}
