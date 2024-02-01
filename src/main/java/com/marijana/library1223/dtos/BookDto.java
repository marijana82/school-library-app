package com.marijana.library1223.dtos;

import com.marijana.library1223.models.FileDocument;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Objects;

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
    private FileDocument bookPhoto;


    //constructors
    public BookDto() {}

    public BookDto(Long id, Integer isbn, String bookTitle, String nameAuthor, String nameIllustrator, Integer suitableAge) {}


    //equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookDto bookDto)) return false;
        return Objects.equals(getId(), bookDto.getId()) && Objects.equals(getIsbn(), bookDto.getIsbn()) && Objects.equals(getBookTitle(), bookDto.getBookTitle()) && Objects.equals(getNameAuthor(), bookDto.getNameAuthor()) && Objects.equals(getNameIllustrator(), bookDto.getNameIllustrator()) && Objects.equals(getSuitableAge(), bookDto.getSuitableAge()) && Objects.equals(getBookPhoto(), bookDto.getBookPhoto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIsbn(), getBookTitle(), getNameAuthor(), getNameIllustrator(), getSuitableAge(), getBookPhoto());
    }
}


