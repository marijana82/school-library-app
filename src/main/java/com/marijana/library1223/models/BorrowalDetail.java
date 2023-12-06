package com.marijana.library1223.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDate;

@Entity
@Table(name="borrowal_details")
public class BorrowalDetail {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="borrowal_date")
    private LocalDate borrowalDate;
    @Column(name="due_date")
    private LocalDate dueDate;

    //private Status status (take from enumeration);


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBorrowalDate() {
        return borrowalDate;
    }

    public void setBorrowalDate(LocalDate borrowalDate) {
        this.borrowalDate = borrowalDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

}
