package com.marijana.library1223.controllers;

//only used for the "in between class" AuthorBook
//it only contains post-method to add author to book
//no get method because this is only backend implementation, 
// user is not aware of its existence

import com.marijana.library1223.models.AuthorBookKey;
import com.marijana.library1223.services.AuthorBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author-book")
public class AuthorBookController {

    private final AuthorBookService authorBookService;

    public AuthorBookController(AuthorBookService authorBookService) {
        this.authorBookService = authorBookService;
    }

    @PostMapping("/{idAuthor}/{idBook}")
    public ResponseEntity<AuthorBookKey> addAuthorBook(@PathVariable("idAuthor") Long idAuthor, @PathVariable("idBook") Long idBook) {
        AuthorBookKey key = authorBookService.addAuthorBook(idAuthor, idBook);
        return ResponseEntity.created(null).body(key);
    }



}
