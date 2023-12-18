package com.marijana.library1223.services;

import com.marijana.library1223.dtos.BookCopyDto;
import com.marijana.library1223.exceptions.RecordNotFoundException;
import com.marijana.library1223.exceptions.ResourceAlreadyExistsException;
import com.marijana.library1223.exceptions.ResourceNotFoundException;
import com.marijana.library1223.models.Book;
import com.marijana.library1223.models.BookCopy;
import com.marijana.library1223.repositories.BookCopyRepository;
import com.marijana.library1223.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookCopyService {

    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;

    public BookCopyService(BookCopyRepository bookCopyRepository, BookRepository bookRepository, BookService bookService) {
        this.bookCopyRepository = bookCopyRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    //createNewBookCopy method - post mapping
    public BookCopyDto createBookCopy(BookCopyDto bookCopyDto) {
        BookCopy bookCopy = new BookCopy();
        bookCopy.setBarcode(bookCopyDto.getBarcode());
        bookCopy.setNumberOfPages(bookCopyDto.getNumberOfPages());
        bookCopy.setTotalWordCount(bookCopyDto.getTotalWordCount());
        bookCopy.setFormat(bookCopyDto.getFormat());
        bookCopy.setInWrittenForm(bookCopyDto.isInWrittenForm());
        bookCopy.setAudioBook(bookCopyDto.isAudioBook());
        bookCopy.setDyslexiaFriendly(bookCopyDto.isDyslexiaFriendly());
        bookCopy.setYearPublished(bookCopyDto.getYearPublished());
        bookCopyRepository.save(bookCopy);
        bookCopyDto.setId(bookCopy.getId());
        return bookCopyDto;
    }

    //showOneCopy - get mapping(one)
    public BookCopyDto showOneCopy(Long id) {
        Optional<BookCopy> optionalBookCopy = bookCopyRepository.findById(id);
        if(optionalBookCopy.isPresent()) {
            BookCopy requestedBookCopy = optionalBookCopy.get();
            return transferBookCopyToBookCopyDto(requestedBookCopy);
        } else {
            throw new ResourceNotFoundException("Book copy with id number " + id + " has not been found.");
        }
    }

    //show all copies - get mapping (all)
    public List<BookCopyDto> getAllBookCopies() {
        List<BookCopy> bookCopyList = bookCopyRepository.findAll();
        List<BookCopyDto> bookCopyDtoList = new ArrayList<>();
        for(BookCopy bookCopy : bookCopyList) {
            BookCopyDto bookCopyDto = transferBookCopyToBookCopyDto(bookCopy);
            bookCopyDtoList.add(bookCopyDto);
        }
        return bookCopyDtoList;
    }

    //show all copies published after
    public List<BookCopyDto> getAllBookCopiesPublishedAfter(LocalDate date) {
        List<BookCopy> bookCopyList = bookCopyRepository.findByYearPublishedAfter(date);
        List<BookCopyDto> bookCopyDtoList = new ArrayList<>();
        for(BookCopy bookCopy : bookCopyList) {
            BookCopyDto bookCopyDto = transferBookCopyToBookCopyDto(bookCopy);
            bookCopyDtoList.add(bookCopyDto);
        }
        return bookCopyDtoList;
    }

    public List<BookCopyDto> getAllBookCopiesDyslexiaFriendly(boolean dyslexia) {
        List<BookCopy> bookCopyList = bookCopyRepository.findByDyslexiaFriendly(dyslexia);
        List<BookCopyDto> bookCopyDtoList = new ArrayList<>();
        for(BookCopy bookCopy : bookCopyList) {
            BookCopyDto bookCopyDto = transferBookCopyToBookCopyDto(bookCopy);
            bookCopyDtoList.add(bookCopyDto);
        }
        return bookCopyDtoList;
    }



    public List<BookCopyDto> getAllBookCopiesAudio(boolean audio) {
        List<BookCopy> bookCopyList = bookCopyRepository.findByAudioBook(audio);
        List<BookCopyDto> bookCopyDtoList = new ArrayList<>();
        for(BookCopy bookCopy : bookCopyList) {
            BookCopyDto bookCopyDto = transferBookCopyToBookCopyDto(bookCopy);
            bookCopyDtoList.add(bookCopyDto);
        }
        return bookCopyDtoList;
    }

    //updateOneBookCopy
    public BookCopyDto updateOneBookCopy(Long id, BookCopyDto bookCopyDto) {
        Optional<BookCopy> optionalBookCopy = bookCopyRepository.findById(id);
        if(optionalBookCopy.isEmpty()) {
            throw new RecordNotFoundException("Book copy with id number " + id + " not found.");
        } else {
            BookCopy bookCopyFound = optionalBookCopy.get();
            BookCopy bookCopyUpdate = transferBookCopyDtoToBookCopy(bookCopyDto);
            bookCopyUpdate.setId(bookCopyFound.getId());
            return transferBookCopyToBookCopyDto(bookCopyUpdate);
        }


    }




    //helper methods ...........................................

    //helper method - transfer BookCopy to BookCopyDto
    public BookCopyDto transferBookCopyToBookCopyDto(BookCopy bookCopy) {
        BookCopyDto bookCopyDto = new BookCopyDto();
        bookCopyDto.setId(bookCopy.getId());
        bookCopyDto.setBarcode(bookCopy.getBarcode());
        bookCopyDto.setFormat(bookCopy.getFormat());
        bookCopyDto.setNumberOfPages(bookCopy.getNumberOfPages());
        bookCopyDto.setTotalWordCount(bookCopy.getTotalWordCount());
        bookCopyDto.setAudioBook(bookCopy.isAudioBook());
        bookCopyDto.setInWrittenForm(bookCopy.isInWrittenForm());
        bookCopyDto.setDyslexiaFriendly(bookCopy.isDyslexiaFriendly());
        bookCopyDto.setYearPublished(bookCopy.getYearPublished());
        bookCopyDto.setBook(bookService.transferBookToBookDto(bookCopy.getBook()));
        return bookCopyDto;
    }


    //helper method - transfer BookCopyDto to BookCopy
    public BookCopy transferBookCopyDtoToBookCopy(BookCopyDto bookCopyDto) {
        BookCopy bookCopy = new BookCopy();
        bookCopy.setId(bookCopyDto.getId());
        bookCopy.setBarcode(bookCopyDto.getBarcode());
        bookCopy.setFormat(bookCopyDto.getFormat());
        bookCopy.setNumberOfPages(bookCopyDto.getNumberOfPages());
        bookCopy.setTotalWordCount(bookCopyDto.getTotalWordCount());
        bookCopy.setAudioBook(bookCopyDto.isAudioBook());
        bookCopy.setInWrittenForm(bookCopyDto.isInWrittenForm());
        bookCopy.setDyslexiaFriendly(bookCopyDto.isDyslexiaFriendly());
        bookCopy.setYearPublished(bookCopyDto.getYearPublished());
        return bookCopy;
    }

    //assign Book to BookCopy
    public void assignBookToBookCopy(Long idBookCopy, Long idBook) {
        Optional<BookCopy> optionalBookCopy = bookCopyRepository.findById(idBookCopy);
        Optional<Book> optionalBook = bookRepository.findById(idBook);

        if(optionalBookCopy.isPresent() && optionalBook.isPresent()) {
            BookCopy bookCopyIsPresent = optionalBookCopy.get();
            Book bookIsPresent = optionalBook.get();

            bookCopyIsPresent.setBook(bookIsPresent);
            bookCopyRepository.save(bookCopyIsPresent);
        } else {
            throw new RecordNotFoundException("Book copy not found");
        }

    }


}
