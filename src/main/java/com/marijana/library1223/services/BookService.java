package com.marijana.library1223.services;

import com.marijana.library1223.dtos.BookDto;
import com.marijana.library1223.exceptions.IdNotFoundException;
import com.marijana.library1223.exceptions.RecordNotFoundException;
import com.marijana.library1223.exceptions.ResourceNotFoundException;
import com.marijana.library1223.fileUploadResponse.FileUploadResponse;
import com.marijana.library1223.models.*;
import com.marijana.library1223.repositories.BookRepository;
import com.marijana.library1223.repositories.FileUploadRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final FileUploadRepository fileUploadRepository;
    private final ReviewBookService reviewBookService;


    public BookService(BookRepository bookRepository, FileUploadRepository fileUploadRepository, ReviewBookService reviewBookService) {
        this.bookRepository = bookRepository;
        this.fileUploadRepository = fileUploadRepository;
        this.reviewBookService = reviewBookService;
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

            BookDto requestedBookDto = transferBookToBookDto(optionalBook.get());
            FileDocument bookPhoto = optionalBook.get().getBookPhoto();

            if(bookPhoto != null) {
                requestedBookDto.setBookPhoto(optionalBook.get().getBookPhoto());
            }

            //TODO: ADD REVIEW BY FOLLOWING THE SAME STEPS AS FOR BOOK PHOTO

            return requestedBookDto;


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

            Book existingBook = bookOptional.get();

            Book partialUpdates = transferBookDtoToBook(bookDto);

            partialUpdates.setId(existingBook.getId());

            if(partialUpdates.getIsbn() != null) {
                existingBook.setIsbn(partialUpdates.getIsbn());
            }

            if(partialUpdates.getBookTitle() != null) {
                existingBook.setBookTitle(partialUpdates.getBookTitle());
            }

            if(partialUpdates.getNameAuthor() != null) {
                existingBook.setNameAuthor(partialUpdates.getNameAuthor());
            }

            if(partialUpdates.getNameIllustrator() != null) {
                existingBook.setNameIllustrator(partialUpdates.getNameIllustrator());
            }

            if(partialUpdates.getSuitableAge() != null) {
                existingBook.setSuitableAge(partialUpdates.getSuitableAge());
            }

            if(partialUpdates.getBookPhoto() != null) {
                existingBook.setBookPhoto(partialUpdates.getBookPhoto());
            }

            Book newBookSaved = bookRepository.save(existingBook);

            return transferBookToBookDto(newBookSaved);


        } else {

            throw new IdNotFoundException("Book with id " + id + " cannot be updated.");
        }
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
        bookDto.setBookPhoto(book.getBookPhoto());
        return bookDto;
    }



    public Book transferBookDtoToBook(BookDto bookDto) {
        Book book = new Book();
        //book.setId(bookDto.getId());
        book.setIsbn(bookDto.getIsbn());
        book.setBookTitle(bookDto.getBookTitle());
        book.setNameAuthor(bookDto.getNameAuthor());
        book.setNameIllustrator(bookDto.getNameIllustrator());
        book.setSuitableAge(bookDto.getSuitableAge());
        book.setBookPhoto(bookDto.getBookPhoto());
        return book;
    }

    public void assignPhotoToBook(String fileName, Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        Optional<FileDocument> optionalFileDocument = fileUploadRepository.findByFileName(fileName);

        if(optionalBook.isPresent() && optionalFileDocument.isPresent()) {

            Book book = optionalBook.get();

            FileDocument uploadPhoto = optionalFileDocument.get();

            book.setBookPhoto(uploadPhoto);

            bookRepository.save(book);

        } else {

            throw new RecordNotFoundException("Photo with file name " + fileName + "does not exist in the database.");

        }
    }



}
