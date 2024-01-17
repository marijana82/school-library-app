package com.marijana.library1223.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowalDto {
    private Long id;
    @FutureOrPresent
    private LocalDate dateOfBorrowal;
    @FutureOrPresent
    private LocalDate dueDate;
    @NotBlank(message="Please provide book title.")
    private String bookTitle;
    @Max(value=3)
    private Integer numberOfBooksBorrowed;
    //---relation
    private ReservationDto reservationDto;
    //---relation with account and book copy
    private AccountDto accountDto;
    private BookCopyDto bookCopyDto;

}
