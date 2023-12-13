package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.BookDto;
import com.marijana.library1223.services.AuthorBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController("/authors")
public class AuthorController {


    private final AuthorBookService authorBookService;

    public AuthorController(AuthorBookService authorBookService) {
        this.authorBookService = authorBookService;
    }

    //method to get all books connected to a certain author
    @GetMapping("/books/{idAuthor}")
    public ResponseEntity<Collection<BookDto>> getBooksByIdAuthor(@PathVariable("idAuthor") Long idAuthor) {
        return ResponseEntity.ok(authorBookService.getBooksByIdAuthor(idAuthor));
    }
}
