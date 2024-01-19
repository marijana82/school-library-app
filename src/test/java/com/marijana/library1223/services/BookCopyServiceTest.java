package com.marijana.library1223.services;

import com.marijana.library1223.dtos.BookCopyDto;
import com.marijana.library1223.exceptions.IdNotFoundException;
import com.marijana.library1223.exceptions.ResourceNotFoundException;
import com.marijana.library1223.models.BookCopy;
import com.marijana.library1223.repositories.BookCopyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookCopyServiceTest {

    @Mock
    BookCopyRepository bookCopyRepository;

    @InjectMocks
    BookCopyService bookCopyService;



    @Test
    @DisplayName("Should create book copy")
    void createBookCopy() {
        //1. DTO  MADE AND FILLED
        BookCopyDto bookCopyDto = new BookCopyDto();
        bookCopyDto.setId(1L);
        bookCopyDto.setAudioBook(true);
        bookCopyDto.setDyslexiaFriendly(true);
        bookCopyDto.setFormat("audio");
        bookCopyDto.setBarcode(12345);
        bookCopyDto.setInWrittenForm(false);
        bookCopyDto.setYearPublished(LocalDate.ofEpochDay(2023-01-01));
        bookCopyDto.setNumberOfPages(100);
        bookCopyDto.setTotalWordCount(20000);

        BookCopy bookCopy = new BookCopy();
        bookCopy.setId(bookCopyDto.getId());
        bookCopy.setAudioBook(bookCopyDto.isAudioBook());
        bookCopy.setDyslexiaFriendly(bookCopyDto.isDyslexiaFriendly());
        bookCopy.setBarcode(bookCopyDto.getBarcode());
        bookCopy.setFormat(bookCopyDto.getFormat());
        bookCopy.setInWrittenForm(bookCopyDto.isInWrittenForm());
        bookCopy.setYearPublished(bookCopyDto.getYearPublished());
        bookCopy.setNumberOfPages(bookCopyDto.getNumberOfPages());
        bookCopy.setTotalWordCount(bookCopyDto.getTotalWordCount());

        Mockito.when(bookCopyRepository.save(Mockito.any(BookCopy.class))).thenReturn(bookCopy);

        BookCopyDto bookCopyDto1 = bookCopyService.createBookCopy(bookCopyDto);

        assertEquals(true, bookCopyDto1.isAudioBook());
        assertEquals(true, bookCopyDto1.isDyslexiaFriendly());
        assertEquals(false, bookCopyDto1.isInWrittenForm());
        assertEquals("audio", bookCopyDto1.getFormat());
        assertEquals(12345, bookCopyDto1.getBarcode());
        assertEquals(LocalDate.ofEpochDay(2023-01-01), bookCopyDto1.getYearPublished());
        assertEquals(100, bookCopyDto1.getNumberOfPages());
        assertEquals(20000, bookCopyDto1.getTotalWordCount());
    }

    @Test
    @DisplayName("Should show one copy")
    void showOneCopy() {
        BookCopyDto bookCopyDto = new BookCopyDto();
        bookCopyDto.setId(1L);
        bookCopyDto.setAudioBook(true);
        bookCopyDto.setDyslexiaFriendly(true);
        bookCopyDto.setFormat("audio");
        bookCopyDto.setBarcode(12345);
        bookCopyDto.setInWrittenForm(false);
        bookCopyDto.setYearPublished(LocalDate.ofEpochDay(2023-01-01));
        bookCopyDto.setNumberOfPages(100);
        bookCopyDto.setTotalWordCount(20000);

        BookCopy bookCopy = new BookCopy();
        bookCopy.setId(bookCopyDto.getId());
        bookCopy.setAudioBook(bookCopyDto.isAudioBook());
        bookCopy.setDyslexiaFriendly(bookCopyDto.isDyslexiaFriendly());
        bookCopy.setBarcode(bookCopyDto.getBarcode());
        bookCopy.setFormat(bookCopyDto.getFormat());
        bookCopy.setInWrittenForm(bookCopyDto.isInWrittenForm());
        bookCopy.setYearPublished(bookCopyDto.getYearPublished());
        bookCopy.setNumberOfPages(bookCopyDto.getNumberOfPages());
        bookCopy.setTotalWordCount(bookCopyDto.getTotalWordCount());

        Mockito.when(bookCopyRepository.findById(bookCopy.getId())).thenReturn(Optional.of(bookCopy));

        BookCopyDto bookCopyDto1 = bookCopyService.showOneCopy(bookCopy.getId());
        assertEquals(true, bookCopyDto1.isAudioBook());
        assertEquals(true, bookCopyDto1.isDyslexiaFriendly());
        assertEquals(false, bookCopyDto1.isInWrittenForm());
        assertEquals("audio", bookCopyDto1.getFormat());
        assertEquals(12345, bookCopyDto1.getBarcode());
        assertEquals(LocalDate.ofEpochDay(2023-01-01), bookCopyDto1.getYearPublished());
        assertEquals(100, bookCopyDto1.getNumberOfPages());
        assertEquals(20000, bookCopyDto1.getTotalWordCount());
        assertThrows(ResourceNotFoundException.class, () -> {
            bookCopyService.showOneCopy(null);
        });
    }

    @Test
    @DisplayName("Should get all book copies")
    void getAllBookCopies() {
        BookCopy bookCopy1 = new BookCopy();
        bookCopy1.setId(1L);
        bookCopy1.setAudioBook(true);
        bookCopy1.setDyslexiaFriendly(true);
        bookCopy1.setBarcode(12345);
        bookCopy1.setFormat("audio");
        bookCopy1.setInWrittenForm(false);
        bookCopy1.setYearPublished(LocalDate.ofEpochDay(2023-01-01));
        bookCopy1.setNumberOfPages(100);
        bookCopy1.setTotalWordCount(20000);

        BookCopy bookCopy2 = new BookCopy();
        bookCopy2.setId(2L);
        bookCopy2.setAudioBook(false);
        bookCopy2.setDyslexiaFriendly(false);
        bookCopy2.setBarcode(12345);
        bookCopy2.setFormat("hardcover");
        bookCopy2.setInWrittenForm(true);
        bookCopy2.setYearPublished(LocalDate.ofEpochDay(2023-01-01));
        bookCopy2.setNumberOfPages(100);
        bookCopy2.setTotalWordCount(20000);

        List<BookCopy> bookCopyList = new ArrayList<>();
        bookCopyList.add(bookCopy1);
        bookCopyList.add(bookCopy2);

        Mockito.when(bookCopyRepository.findAll()).thenReturn(bookCopyList);

        List<BookCopyDto> bookCopyDtoList = bookCopyService.getAllBookCopies();

        assertEquals(2, bookCopyDtoList.size());
    }



    @Test
    @DisplayName("Should get all book copies published after")
    void getAllBookCopiesPublishedAfter() {

        BookCopy bookCopy1 = new BookCopy();
        bookCopy1.setId(1L);
        bookCopy1.setAudioBook(true);
        bookCopy1.setDyslexiaFriendly(true);
        bookCopy1.setBarcode(12345);
        bookCopy1.setFormat("audio");
        bookCopy1.setInWrittenForm(false);
        bookCopy1.setYearPublished(LocalDate.ofEpochDay(2023-01-01));
        bookCopy1.setNumberOfPages(100);
        bookCopy1.setTotalWordCount(20000);

        BookCopy bookCopy2 = new BookCopy();
        bookCopy2.setId(2L);
        bookCopy2.setAudioBook(false);
        bookCopy2.setDyslexiaFriendly(false);
        bookCopy2.setBarcode(12345);
        bookCopy2.setFormat("hardcover");
        bookCopy2.setInWrittenForm(true);
        bookCopy2.setYearPublished(LocalDate.ofEpochDay(2023-10-10));
        bookCopy2.setNumberOfPages(100);
        bookCopy2.setTotalWordCount(20000);

        List<BookCopy> bookCopyList = new ArrayList<>();
        bookCopyList.add(bookCopy1);
        bookCopyList.add(bookCopy2);

        Mockito.when(bookCopyRepository.findByYearPublishedAfter(LocalDate.ofEpochDay(2023-05-05))).thenReturn(bookCopyList);

        List<BookCopyDto> bookCopyDtoList = bookCopyService.getAllBookCopiesPublishedAfter(LocalDate.ofEpochDay(2023-05-05));

        assertEquals(2L, bookCopyDtoList.get(1).getId());
        assertEquals(false, bookCopyDtoList.get(1).isAudioBook());
        assertEquals(false, bookCopyDtoList.get(1).isDyslexiaFriendly());
        //TODO: FILL THE REST
    }



    @Test
    void getAllBookCopiesDyslexiaFriendly() {
    }

    @Test
    void getAllBookCopiesAudio() {
    }



    @Test
    void updateOneBookCopy() {

    }

    @Test
    void deleteCopyById() {

        //Arrange
        BookCopy bookCopy1 = new BookCopy();
        bookCopy1.setId(1L);
        bookCopy1.setAudioBook(true);
        bookCopy1.setDyslexiaFriendly(true);
        bookCopy1.setBarcode(12345);
        bookCopy1.setFormat("audio");
        bookCopy1.setInWrittenForm(false);
        bookCopy1.setYearPublished(LocalDate.ofEpochDay(2023-01-01));
        bookCopy1.setNumberOfPages(100);
        bookCopy1.setTotalWordCount(20000);

        when(bookCopyRepository.existsById(1000L)).thenReturn(true);
        when(bookCopyRepository.existsById(null)).thenReturn(false);
        when(bookCopyRepository.findById(1000L)).thenReturn(Optional.of(bookCopy1));
        //Act
        bookCopyService.deleteCopyById(1000L);
        //Assert
        verify(bookCopyRepository).delete(bookCopy1);
        //Assert in case the book is not found
        assertThrows(IdNotFoundException.class, () -> {
            bookCopyService.deleteCopyById(null);
        });

    }

    @Test
    void transferBookCopyToBookCopyDto() {
    }

    @Test
    void transferBookCopyDtoToBookCopy() {
    }

    @Test
    void assignBookToBookCopy() {
    }
}