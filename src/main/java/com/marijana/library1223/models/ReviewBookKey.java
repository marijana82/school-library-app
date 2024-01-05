package com.marijana.library1223.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
//embeddable so that it can be used in ReviewBook class as EmbeddedId
@Embeddable
public class ReviewBookKey implements Serializable {

    @Column(name = "review_id")
    private Long reviewId;
    @Column(name = "book_id")
    private Long bookId;
}
