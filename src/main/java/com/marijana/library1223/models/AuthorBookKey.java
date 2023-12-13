package com.marijana.library1223.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
//embeddable so that it can be used in AuthorBook class as EmbeddedId
@Embeddable
public class AuthorBookKey implements Serializable {

    @Column(name = "author_id")
    private Long authorId;
    @Column(name = "book_id")
    private Long bookId;

    //constructor created by data
    //getters&setters created by data
}
