package com.marijana.library1223.models;

import jakarta.persistence.*;

@Entity
public class AuthorBook {

    //embedded id - so that there's no new id created
    @EmbeddedId
    private AuthorBook id;

    //this is the owner of the relation
    //there's a foreign key in the database
    //why sometimes Lazy and sometimes Eager?
    @ManyToOne(fetch = FetchType.LAZY)
    //@MapsId makes it happen that the variables get combined to a key?
    @MapsId
    @JoinColumn(name = "author_id")
    private Author author;

    //this is the owner of the relation
    //there's a foreign key in the database
    @ManyToOne
    @MapsId("book_id")
    @JoinColumn(name = "book_id")
    private Book book;
}
