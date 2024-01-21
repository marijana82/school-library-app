package com.marijana.library1223.services;

import com.marijana.library1223.dtos.BookDto;
import com.marijana.library1223.exceptions.IdNotFoundException;
import com.marijana.library1223.exceptions.RecordNotFoundException;
import com.marijana.library1223.models.*;
import com.marijana.library1223.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public BookDto createNewBook(BookDto bookDto) {
        Book book = new Book();
        book.setIsbn(bookDto.getIsbn());
        book.setBookTitle(bookDto.getBookTitle());
        book.setNameAuthor(bookDto.getNameAuthor());
        book.setNameIllustrator(bookDto.getNameIllustrator());
        book.setSuitableAge(bookDto.getSuitableAge());
        bookRepository.save(book);
        bookDto.setId(book.getId());
        return bookDto;
    }


    public BookDto showOneBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            Book requestedBook = optionalBook.get();
            return transferBookToBookDto(requestedBook);
        } else {
            throw new RecordNotFoundException("Book with id number " + id + " has not been found.");
        }
    }


    public List<BookDto> showAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        List<BookDto> bookDtoList = new ArrayList<>();
        for(Book book : bookList) {
            BookDto bookDto = transferBookToBookDto(book);
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }


    public List<BookDto> showAllBooksByNameAuthor(String nameAuthor) {
        List<Book> bookList = bookRepository.findAllBooksByNameAuthorEqualsIgnoreCase(nameAuthor);
        List<BookDto> bookDtoList = new ArrayList<>();
        for(Book book : bookList) {
            BookDto bookDto = transferBookToBookDto(book);
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }


    public List<BookDto> showAllBooksByNameIllustrator(String nameIllustrator) {
        List<Book> bookList = bookRepository.findAllBooksByNameIllustratorEqualsIgnoreCase(nameIllustrator);
        List<BookDto> bookDtoList = new ArrayList<>();
        for(Book book : bookList) {
            BookDto bookDto = transferBookToBookDto(book);
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }


    public List<BookDto> showAllBooksByNameIllustratorAndNameAuthor(String nameIllustrator, String nameAuthor) {
        List<Book> bookList = bookRepository.findAllBooksByNameIllustratorAndNameAuthorEqualsIgnoreCase(nameIllustrator, nameAuthor);
        List<BookDto> bookDtoList = new ArrayList<>();
        for(Book book : bookList) {
            BookDto bookDto = transferBookToBookDto(book);
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }



    public String deleteById(Long id) {
        if(bookRepository.existsById(id)) {
            Optional<Book> bookFound = bookRepository.findById(id);
            Book bookToDelete = bookFound.get();
            bookRepository.delete(bookToDelete);
            return "Book with id number " + id + " has been successfully deleted.";
        } else {
            throw new IdNotFoundException("Book with id number " + id + " has not been found.");
        }

    }


    public BookDto updateOneBook(Long id, BookDto bookDto) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isEmpty()) {
            throw new IdNotFoundException("Book cannot be updated as it does not exist in the database.");
        } else {
            Book book = optionalBook.get();
            Book updatedBook = transferBookDtoToBook(bookDto);
            updatedBook.setId(book.getId());
            bookRepository.save(updatedBook);
            return transferBookToBookDto(updatedBook);
        }
    }


    //updateBookPartially
    public BookDto updateBookPartially(Long id, BookDto bookDto) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if(bookRepository.existsById(id)) {

            Book bookToUpdate = bookOptional.get();
            Book bookTransfer = transferBookDtoToBook(bookDto);
            bookTransfer.setId(bookToUpdate.getId());

            if(bookDto.getIsbn() != -1) {
                bookToUpdate.setIsbn(bookDto.getIsbn());
            }

            if(bookDto.getBookTitle() != null) {
                bookToUpdate.setBookTitle(bookDto.getBookTitle());
            }

            if(bookDto.getNameAuthor() != null) {
                bookToUpdate.setNameIllustrator(bookDto.getNameIllustrator());
            }

            if(bookDto.getSuitableAge() != -1) {
                bookToUpdate.setSuitableAge(bookDto.getSuitableAge());
            }

            Book savedBook = bookRepository.save(bookToUpdate);
            return transferBookToBookDto(savedBook);


        } else {

            throw new IdNotFoundException("Book with id " + id + " cannot be updated.");
        }


        /*if(bookOptional.isEmpty()) {
            throw new IdNotFoundException("Book with id " + id + " cannot be updated.");

        } else {

            Book bookToUpdate = bookOptional.get();

            Book book1 = transferBookDtoToBook(bookDto);
            book1.setId(bookToUpdate.getId());

            if(bookDto.getIsbn() !=-1) {
                bookToUpdate.setIsbn(bookDto.getIsbn());
            }
            if(bookDto.getBookTitle() !=null) {
                bookToUpdate.setBookTitle(bookDto.getBookTitle());
            }
            if(bookDto.getNameAuthor() !=null) {
                bookToUpdate.setNameAuthor(bookDto.getNameAuthor());
            }
            if(bookDto.getNameIllustrator() !=null) {
                bookToUpdate.setNameIllustrator(bookDto.getNameIllustrator());
            }

            if(bookDto.getSuitableAge() !=-1) {
                bookToUpdate.setSuitableAge(bookDto.getSuitableAge());
            }

            Book savedBook = bookRepository.save(bookToUpdate);
            return transferBookToBookDto(savedBook);

        }*/

    }



    //helper methods ...........................................
    public BookDto transferBookToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setBookTitle(book.getBookTitle());
        bookDto.setNameAuthor(book.getNameAuthor());
        bookDto.setNameIllustrator(book.getNameIllustrator());
        bookDto.setSuitableAge(book.getSuitableAge());
        return bookDto;
    }



    public Book transferBookDtoToBook(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setIsbn(bookDto.getIsbn());
        book.setBookTitle(bookDto.getBookTitle());
        book.setNameAuthor(bookDto.getNameAuthor());
        book.setNameIllustrator(bookDto.getNameIllustrator());
        book.setSuitableAge(bookDto.getSuitableAge());
        return book;
    }

}
