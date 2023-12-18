package com.marijana.library1223.dtos;

import com.marijana.library1223.models.BookCopy;
import com.marijana.library1223.models.InformationBook;
import com.marijana.library1223.models.ReadingBook;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class BookDto {

    private Long id;
    @Min(4)
    private int isbn;
    @NotBlank(message = "Please provide a book title.")
    private String bookTitle;
    @NotBlank(message = "Please provide name of the author.")
    private String nameAuthor;
    private String nameIllustrator;
    @Positive(message = "Suitable age must be a positive number.")
    private int suitableAge;

    private InformationBook informationBook;
    private ReadingBook readingBook;

    //many-to-one relation (many books to one reservation id)
    //public Long reservationId;
    private ReservationDto reservationDto;

    //one-to-many
    //private List<BookCopyDto> bookCopyList;

}


