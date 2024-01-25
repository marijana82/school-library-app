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
    private Integer numberOfBooksBorrowed;
    //---relations
    private ReservationDto reservationDto;
    private AccountDto accountDto;
    private BookCopyDto bookCopyDto;

}
