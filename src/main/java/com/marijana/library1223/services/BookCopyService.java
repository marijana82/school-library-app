package com.marijana.library1223.services;

import com.marijana.library1223.dtos.BookCopyDto;
import com.marijana.library1223.exceptions.ResourceNotFoundException;
import com.marijana.library1223.models.BookCopy;
import com.marijana.library1223.repositories.BookCopyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookCopyService {

    private final BookCopyRepository bookCopyRepository;

    public BookCopyService(BookCopyRepository bookCopyRepository) {
        this.bookCopyRepository = bookCopyRepository;
    }

    //createNewBookCopy method - post mapping
    public BookCopyDto createNewBookCopy(BookCopyDto bookCopyDto) {
        BookCopy bookCopy = new BookCopy();
        bookCopy.setBarcode(bookCopyDto.getBarcode());
        bookCopy.setAudioBook(bookCopyDto.isAudioBook());
        bookCopy.setDyslexiaFriendly(bookCopyDto.isDyslexiaFriendly());
        bookCopy.setInWrittenForm(bookCopyDto.isInWrittenForm());
        bookCopy.setNumberOfPages(bookCopyDto.getNumberOfPages());
        bookCopy.setTotalWordCount(bookCopyDto.getTotalWordCount());
        bookCopy.setYearPublished(bookCopyDto.getYearPublished());
        bookCopy.setFormat(bookCopyDto.getFormat());
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




    //helper methods ...........................................

    //helper method - transfer BookCopy to BookCopyDto
    private BookCopyDto transferBookCopyToBookCopyDto(BookCopy bookCopy) {
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
        return bookCopyDto;
    }


    //helper method - transfer BookCopyDto to BookCopy
    private BookCopy transferBookCopyDtoToBookCopy(BookCopyDto bookCopyDto) {
        BookCopy bookCopy = new BookCopy();
        bookCopy.setId(bookCopyDto.getId());
        bookCopy.setBarcode(bookCopyDto.getBarcode());
        bookCopy.setFormat(bookCopyDto.getFormat());
        bookCopy.setNumberOfPages(bookCopyDto.getNumberOfPages());
        bookCopy.setTotalWordCount(bookCopyDto.getTotalWordCount());
        bookCopy.setAudioBook(bookCopyDto.isAudioBook());
        bookCopy.setInWrittenForm(bookCopyDto.isInWrittenForm());
        bookCopy.setDyslexiaFriendly(bookCopyDto.isDyslexiaFriendly());
        bookCopyDto.setYearPublished(bookCopyDto.getYearPublished());
        return bookCopy;
    }





}
