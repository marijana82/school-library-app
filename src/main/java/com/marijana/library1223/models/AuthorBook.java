package com.marijana.library1223.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class AuthorBook {

    //embedded id - so that there's no new id created, use AuthorBookKey id
    @EmbeddedId
    private AuthorBookKey id;

    //this is the owner of the relation
    //there's a foreign key in the database
    //why sometimes Lazy and sometimes Eager?
    @ManyToOne(fetch = FetchType.LAZY)
    //@MapsId makes it happen that the variables get combined to a key?
    @MapsId("authorId")
    @JoinColumn(name = "author_id")
    private Author author;

    //this is the owner of the relation
    //there's a foreign key in the database
    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;
}
