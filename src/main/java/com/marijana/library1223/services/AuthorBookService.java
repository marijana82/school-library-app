package com.marijana.library1223.services;

import com.marijana.library1223.dtos.AuthorDto;
import com.marijana.library1223.dtos.BookDto;
import com.marijana.library1223.exceptions.RecordNotFoundException;
import com.marijana.library1223.models.Author;
import com.marijana.library1223.models.AuthorBook;
import com.marijana.library1223.models.AuthorBookKey;
import com.marijana.library1223.models.Book;
import com.marijana.library1223.repositories.AuthorBookRepository;
import com.marijana.library1223.repositories.AuthorRepository;
import com.marijana.library1223.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//this class contains the methods of AuthorBookController
//this class is different than the other service classes, because this one is autowired in 3 different controllers

@Service
public class AuthorBookService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AuthorBookRepository authorBookRepository;

    public AuthorBookService(AuthorRepository authorRepository, BookRepository bookRepository, AuthorBookRepository authorBookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.authorBookRepository = authorBookRepository;
    }

    //getAuthorsByBookId
    public Collection<AuthorDto> getAuthorsByIdBook(Long idBook) {
        Collection<AuthorDto> authorDtos = new HashSet<>();
        Collection<AuthorBook> authorBooks = authorBookRepository.findAllByBookId(idBook);
        for(AuthorBook authorBook : authorBooks) {
            Author author = authorBook.getAuthor();
            AuthorDto authorDto = new AuthorDto();
            authorDto.setCountry(author.getCountry());
            authorDto.setId(author.getId());
            authorDto.setName(author.getName());
            authorDtos.add(authorDto);
        }
        return authorDtos;
    }

    //get BooksByIdAuthor
    public Collection<BookDto> getBooksByIdAuthor(Long idAuthor) {
        //set to avoid double entries
        Set<BookDto> bookSetDtos = new HashSet<>();
        List<AuthorBook> authorBookList = authorBookRepository.findAllByAuthorId(idAuthor);
        for(AuthorBook authorBook : authorBookList) {
            Book book = authorBook.getBook();
            BookDto bookDto = new BookDto();
            bookDto.setBookTitle(book.getBookTitle());
            bookDto.setId(book.getId());
            bookDto.setNameAuthor(book.getNameAuthor());
            bookDto.setIsbn(book.getIsbn());
            bookDto.setNameIllustrator(book.getNameIllustrator());
            bookDto.setSuitableAge(book.getSuitableAge());
            bookDto.setInformationBook(book.getInformationBook());
            bookDto.setReadingBook(book.getReadingBook());
            //bookDto.setReservationDto(book.getReservation());
            //bookDto.setReservationId();
            //bookDto.setBookCopyList(book.getBookCopyList());
            bookSetDtos.add(bookDto);
        }
        return bookSetDtos;
    }

    //addAuthorBook
    public AuthorBookKey addAuthorBook(Long idAuthor, Long idBook) {
        AuthorBook authorBook = new AuthorBook();

        if(!authorRepository.existsById(idAuthor)) {
            throw new RecordNotFoundException();
        }
        Author author = authorRepository.findById(idAuthor).orElse(null);

        if(!bookRepository.existsById(idBook)) {
            throw new RecordNotFoundException();
        }
        Book book = bookRepository.findById(idBook).orElse(null);

        authorBook.setAuthor(author);
        authorBook.setBook(book);

        //AuthorBookKey id = new AuthorBookKey(idAuthor, idBook); - with this one i'm getting error expected 0 but found 2
        AuthorBookKey id = new AuthorBookKey();
        id.setAuthorId(idAuthor);
        id.setBookId(idBook);

        authorBook.setId(id);

        authorBookRepository.save(authorBook);

        return id;


    }




}
