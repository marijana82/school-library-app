package com.marijana.library1223.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

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

}


