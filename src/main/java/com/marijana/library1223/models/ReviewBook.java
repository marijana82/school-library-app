package com.marijana.library1223.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "review_book")
public class ReviewBook {

    @EmbeddedId
    private ReviewBookKey id;

    //OWNER
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("reviewId")
    @JoinColumn(name = "review_id")
    private Review review;

    //OWNER
    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;
}
