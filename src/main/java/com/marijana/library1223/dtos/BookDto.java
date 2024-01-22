package com.marijana.library1223.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BookDto {

    private Long id;
    @Min(4)
    private Integer isbn;
    @NotBlank(message = "Please provide a book title.")
    private String bookTitle;
    @NotBlank(message = "Please provide name of the author.")
    private String nameAuthor;
    private String nameIllustrator;
    @Positive(message = "Suitable age must be a positive number.")
    private Integer suitableAge;


    //constructor
    public BookDto() {}

    public BookDto(Long id, Integer isbn, String bookTitle, String nameAuthor, String nameIllustrator, Integer suitableAge) {}

}


