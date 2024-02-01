package com.marijana.library1223.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "isbn")
    private Integer isbn;
    @Column(name = "book_title")
    private String bookTitle;
    @Column(name = "name_author")
    private String nameAuthor;
    @Column(name = "name_illustrator")
    private String nameIllustrator;
    @Column(name = "suitable_age")
    private Integer suitableAge;

    @OneToOne
    FileDocument bookPhoto;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "book"
    )
    @JsonIgnore
    private Reservation reservation;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<BookCopy> bookCopyList = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<ReviewBook> reviewBooks = new ArrayList<>();


    //constructors - because @Data did not create them!
    public Book() {}



    //equals and hashcode
    public Book(Long id, Integer isbn, String bookTitle, String nameAuthor, String nameIllustrator, Integer suitableAge) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(getId(), book.getId()) && Objects.equals(getIsbn(), book.getIsbn()) && Objects.equals(getBookTitle(), book.getBookTitle()) && Objects.equals(getNameAuthor(), book.getNameAuthor()) && Objects.equals(getNameIllustrator(), book.getNameIllustrator()) && Objects.equals(getSuitableAge(), book.getSuitableAge()) && Objects.equals(getBookPhoto(), book.getBookPhoto()) && Objects.equals(getReservation(), book.getReservation()) && Objects.equals(getBookCopyList(), book.getBookCopyList()) && Objects.equals(getReviewBooks(), book.getReviewBooks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIsbn(), getBookTitle(), getNameAuthor(), getNameIllustrator(), getSuitableAge(), getBookPhoto(), getReservation(), getBookCopyList(), getReviewBooks());
    }
}

