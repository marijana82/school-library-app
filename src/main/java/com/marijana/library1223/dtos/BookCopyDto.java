package com.marijana.library1223.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookCopyDto {
    private Long id;
    @Min(4)
    private int barcode;
    @Positive(message = "Number of pages must be a positive number.")
    private Integer numberOfPages;
    @Positive(message = "Number of pages must be a positive number.")
    private Integer totalWordCount;
    private String format;
    @NotNull
    private boolean inWrittenForm;
    @NotNull(message = "Field cannot be null, please provide true or false value.")
    //or @AssertTrue (null is also a valid input)
    private boolean audioBook;
    @NotNull(message = "Field cannot be null, please provide true or false value.")
    //or @AssertTrue (null is also a valid input)
    private boolean dyslexiaFriendly;
    @Past
    private LocalDate yearPublished;

    //relation
    private BookDto bookDto;



}
