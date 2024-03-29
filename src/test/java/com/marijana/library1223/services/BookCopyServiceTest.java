package com.marijana.library1223.services;

import com.marijana.library1223.dtos.BookCopyDto;
import com.marijana.library1223.dtos.BookDto;
import com.marijana.library1223.exceptions.IdNotFoundException;
import com.marijana.library1223.exceptions.RecordNotFoundException;
import com.marijana.library1223.exceptions.ResourceNotFoundException;
import com.marijana.library1223.models.Book;
import com.marijana.library1223.models.BookCopy;
import com.marijana.library1223.repositories.BookCopyRepository;
import com.marijana.library1223.repositories.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BookCopyServiceTest {

    @Mock
    BookCopyRepository bookCopyRepository;

    @Mock
    BookRepository bookRepository;

    @Mock
    BookService bookService;

    @InjectMocks
    BookCopyService bookCopyService;

    @Captor
    ArgumentCaptor<BookCopy> captor;
    BookCopy bookCopy;
    BookCopy bookCopy1;



    @Test
    @DisplayName("Should create book copy")
    void createBookCopy() {

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

        Mockito.when(bookCopyRepository.save(any(BookCopy.class))).thenReturn(bookCopy);

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

        BookDto bookDto = new BookDto();
        bookDto.setBookTitle("Book");

        Book book = new Book();
        book.setBookTitle("Book");

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
        bookCopy1.setBook(book);

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

        BookCopyDto bookCopyDto = new BookCopyDto();
        bookCopyDto.setId(1L);
        bookCopyDto.setBarcode(12345);
        bookCopyDto.setAudioBook(true);
        bookCopyDto.setFormat("audio");
        bookCopyDto.setDyslexiaFriendly(true);
        bookCopyDto.setInWrittenForm(false);
        bookCopyDto.setYearPublished(LocalDate.ofEpochDay(2023-01-01));
        bookCopyDto.setNumberOfPages(100);
        bookCopyDto.setTotalWordCount(20000);
        bookCopyDto.setBookDto(bookDto);

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
        assertEquals(12345, bookCopyDtoList.get(1).getBarcode());
        assertEquals("hardcover", bookCopyDtoList.get(1).getFormat());
        assertEquals(true, bookCopyDtoList.get(1).isInWrittenForm());
        assertEquals(LocalDate.ofEpochDay(2023-10-10), bookCopyDtoList.get(1).getYearPublished());
        assertEquals(100, bookCopyDtoList.get(1).getNumberOfPages());
        assertEquals(20000, bookCopyDtoList.get(1).getTotalWordCount());
    }



    @Test
    @DisplayName("Should get all book copies dyslexia friendly")
    void getAllBookCopiesDyslexiaFriendly() {

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

        Mockito.when(bookCopyRepository.findByDyslexiaFriendly(true)).thenReturn(bookCopyList);
        List<BookCopyDto> bookCopyDtoList = bookCopyService.getAllBookCopiesDyslexiaFriendly(true);

        assertEquals(1L, bookCopyDtoList.get(0).getId());
        assertEquals(true, bookCopyDtoList.get(0).isDyslexiaFriendly());
    }

    @Test
    @DisplayName("Should get all book copies audio")
    void getAllBookCopiesAudio() {

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

        Mockito.when(bookCopyRepository.findByAudioBook(true)).thenReturn(bookCopyList);
        List<BookCopyDto> bookCopyDtoList = bookCopyService.getAllBookCopiesAudio(true);

        assertEquals(1L, bookCopyDtoList.get(0).getId());
        assertEquals(true, bookCopyDtoList.get(0).isAudioBook());

    }



    @Test
    @DisplayName("Should update one book copy")
    void updateOneBookCopy() {

        BookCopyDto bookCopyDto = new BookCopyDto(1L, 1234, 50, 500, "hardcover", true, false, false, LocalDate.ofEpochDay(2024-01-01));
        BookCopy bookCopy = new BookCopy(1L, 1234, 50, 500, "hardcover", true, false, false, LocalDate.ofEpochDay(2024-01-01));
        BookCopy bookCopy1 = new BookCopy(1L, 1234, 50, 500, "hardcover", true, false, false, LocalDate.ofEpochDay(2024-01-01));

        when(bookCopyRepository.findById(1L)).thenReturn(Optional.of(bookCopy));
        when(bookCopyRepository.save(any())).thenReturn(bookCopy1);

        //Act
        bookCopyService.updateOneBookCopy(1L, bookCopyDto);

        //Assert
        verify(bookCopyRepository, times(1)).save(captor.capture());
        BookCopy captured = captor.getValue();

        assertEquals(bookCopy.getId(), captured.getId());

        assertThrows(RecordNotFoundException.class, () -> {
            bookCopyService.updateOneBookCopy(null, null);
        });


    }

    @Test
    @DisplayName("Should delete copy by id")
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
    @DisplayName("Should assign book to book copy")
    void assignBookToBookCopy() {
        //Arrange
        BookCopy bookCopy = new BookCopy();
        bookCopy.setId(1L);

        Book book = new Book();
        book.setId(2L);

        when(bookCopyRepository.findById(1L)).thenReturn(Optional.of(bookCopy));
        when(bookRepository.findById(2L)).thenReturn(Optional.of(book));

        bookCopyService.assignBookToBookCopy(bookCopy.getId(), book.getId());

        verify(bookCopyRepository, times(1)).save(bookCopy);

        assertEquals(book, bookCopy.getBook());
        assertThrows(RecordNotFoundException.class, () -> {
            bookCopyService.assignBookToBookCopy(null, null);
        });

}



}