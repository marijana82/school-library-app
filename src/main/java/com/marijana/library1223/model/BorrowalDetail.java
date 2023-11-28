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

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
