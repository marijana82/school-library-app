package com.marijana.library1223.dtos;

import com.marijana.library1223.models.InformationBook;
import com.marijana.library1223.models.ReadingBook;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;


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
    public Long reservationId;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
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

    public String getNameIllustrator() {
        return nameIllustrator;
    }

    public void setNameIllustrator(String nameIllustrator) {
        this.nameIllustrator = nameIllustrator;
    }

    public int getSuitableAge() {
        return suitableAge;
    }

    public void setSuitableAge(int suitableAge) {
        this.suitableAge = suitableAge;
    }

    public InformationBook getInformationBook() {
        return informationBook;
    }

    public void setInformationBook(InformationBook informationBook) {
        this.informationBook = informationBook;
    }

    public ReadingBook getReadingBook() {
        return readingBook;
    }

    public void setReadingBook(ReadingBook readingBook) {
        this.readingBook = readingBook;
    }
}


