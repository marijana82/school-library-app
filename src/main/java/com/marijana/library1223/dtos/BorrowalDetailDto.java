package com.marijana.library1223.dtos;

import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDate;

public class BorrowalDetailDto {
    private Long id;
    @FutureOrPresent(message = "Please provide today's date.")
    private LocalDate borrowalDate;
    @FutureOrPresent(message = "Please provide valid return date.")
    private LocalDate dueDate;


    //getters&setters
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
