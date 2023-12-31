package com.marijana.library1223.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "review_book")
public class ReviewBook {

    //embedded id - so that there's no new id created, use AuthorBookKey id
    @EmbeddedId
    private ReviewBookKey id;

    //this is the owner of the relation
    //there's a foreign key in the database
    //why sometimes Lazy and sometimes Eager?
    @ManyToOne(fetch = FetchType.LAZY)
    //@MapsId makes it happen that the variables get combined to a key?
    @MapsId("reviewId")
    @JoinColumn(name = "review_id")
    private Review review;

    //this is the owner of the relation
    //there's a foreign key in the database
    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;
}
