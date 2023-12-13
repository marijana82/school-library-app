package com.marijana.library1223.services;

import com.marijana.library1223.repositories.AuthorRepository;
import com.marijana.library1223.repositories.BookRepository;
import org.springframework.stereotype.Service;

//this class contains the methods of AuthorBookController
//this class is different than the other service classes, because this one is autowired in 3 different controllers

@Service
public class AuthorBookService {

    private final AuthorRepository authorRepository;

    public AuthorBookService(AuthorRepository authorRepository, BookRepository bookRepository, AuthorBookRepository authorBookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.authorBookRepository = authorBookRepository;
    }



}
