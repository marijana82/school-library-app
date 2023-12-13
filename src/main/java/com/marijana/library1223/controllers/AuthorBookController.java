package com.marijana.library1223.controllers;

//only used for the "in between class" AuthorBook
//it only contains post-method to add author to book
//no get method because this is only backend implementation, 
// user is not aware of its existence

import com.marijana.library1223.services.AuthorBookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author-book")
public class AuthorBookController {

    private final AuthorBookService authorBookService;

    public AuthorBookController(AuthorBookService authorBookService) {
        this.authorBookService = authorBookService;
    }
}
