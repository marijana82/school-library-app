package com.marijana.library1223.services;

import com.marijana.library1223.dtos.BookDto;
import com.marijana.library1223.models.Book;
import com.marijana.library1223.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    //createNewBook method
    public BookDto createNewBook(BookDto bookDto) {

        Book book = new Book();
        book.setIsbn(bookDto.getIsbn());
        book.setBookTitle(bookDto.getBookTitle());
        book.setNameAuthor(bookDto.getNameAuthor());
        book.setSuitableAge(bookDto.getSuitableAge());
        book.setNumberOfCopies(bookDto.getNumberOfCopies());
        bookRepository.save(book);
        bookDto.setId(book.getId());
        return bookDto;
        //private PictureBook pictureBook;
        //private ReadingBook readingBook;
        //private InformationBook informationBook;

    }


    //helper methods ...........................................

    //helper method - transfer Book to BookDto
    private BookDto transferBookToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setBookTitle(book.getBookTitle());
        bookDto.setNameAuthor(book.getNameAuthor());
        bookDto.setSuitableAge(book.getSuitableAge());
        bookDto.setNumberOfCopies(book.getNumberOfCopies());
        return bookDto;
    }


    //helper method - transfer BookDto to Book
    private Book transferBookDtoToBook(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setIsbn(bookDto.getIsbn());
        book.setBookTitle(bookDto.getBookTitle());
        book.setNameAuthor(bookDto.getNameAuthor());
        book.setSuitableAge(bookDto.getSuitableAge());
        book.setNumberOfCopies(bookDto.getNumberOfCopies());
        return book;
    }




}
