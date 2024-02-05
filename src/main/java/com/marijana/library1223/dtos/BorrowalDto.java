package com.marijana.library1223.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowalDto {
    private Long id;
    @FutureOrPresent
    private LocalDate dateOfBorrowal;
    @FutureOrPresent
    private LocalDate dueDate;
    @Max(value = 1, message = "Only 1 book can be added to this borrowal.")
    private Integer numberOfBooksBorrowed;

    private AccountDto accountDto;
    private BookCopyDto bookCopyDto;

}
