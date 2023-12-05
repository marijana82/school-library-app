package com.marijana.library1223.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class BookDto {

    private Long id;
    @NotBlank(message = "Please provide isbn code.")
    private String isbn;

    @NotBlank(message = "Please provide a book title.")
    private String bookTitle;

    @NotBlank(message = "Please provide name of the author.")
    private String nameAuthor;
    @Positive(message= "Suitable age must be a positive number.")
    private int suitableAge;

    private int numberOfCopies;

    //private PictureBook pictureBook;
    //private ReadingBook readingBook;
    //private InformationBook informationBook;


    //getters&setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public int getSuitableAge() {
        return suitableAge;
    }

    public void setSuitableAge(int suitableAge) {
        this.suitableAge = suitableAge;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }
}
