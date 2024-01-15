package com.marijana.library1223.dtosoutput;

import com.marijana.library1223.dtos.ReservationDto;
import com.marijana.library1223.models.InformationBook;
import com.marijana.library1223.models.ReadingBook;
import lombok.Data;

@Data
public class BookOutputDto {

    private Long id;
    private int isbn;
    private String bookTitle;
    private String nameAuthor;
    private String nameIllustrator;
    private int suitableAge;

    private InformationBook informationBook;
    private ReadingBook readingBook;

    //many-to-one relation (many books to one reservation id)
    public Long reservationId;
    private ReservationDto reservationDto;

    //one-to-many
    //private List<BookCopyDto> bookCopyList;

}
