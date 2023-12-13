package com.marijana.library1223.controllers;

import com.marijana.library1223.services.AuthorBookService;
import org.springframework.web.bind.annotation.RestController;

@RestController("/authors")
public class AuthorController {


    private final AuthorBookService authorBookService;

    public AuthorController(AuthorBookService authorBookService) {
        this.authorBookService = authorBookService;
    }
}
