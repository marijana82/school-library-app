package com.marijana.library1223.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDto {
    private Long id;
    @FutureOrPresent
    private LocalDate reservationDate;
    private String sidenote;
    //relations
    private AccountDto accountDto;
    private BookDto bookDto;

}
