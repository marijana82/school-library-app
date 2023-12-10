package com.marijana.library1223.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDto {
    private Long id;
    @FutureOrPresent
    private LocalDate reservationDate;
    @NotBlank(message="Please provide book title.")
    private String bookTitle;
    @Max(value=3)
    private Integer numberOfBooksReserved;
    private String sidenote;

}
