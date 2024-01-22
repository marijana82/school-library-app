package com.marijana.library1223.dtosoutput;

import com.marijana.library1223.dtos.ReservationDto;
import lombok.Data;

@Data
public class BookOutputDto {

    private Long id;
    private int isbn;
    private String bookTitle;
    private String nameAuthor;
    private String nameIllustrator;
    private int suitableAge;

    //many-to-one relation (many books to one reservation id)
    public Long reservationId;
    private ReservationDto reservationDto;


}
