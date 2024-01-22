package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.BookCopyDto;
import com.marijana.library1223.models.Book;
import com.marijana.library1223.models.BookCopy;
import com.marijana.library1223.repositories.BookRepository;
import com.marijana.library1223.services.BookCopyService;
import com.marijana.library1223.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookCopyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookCopyService bookCopyService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    BookCopyDto bookCopyDto1;
    BookCopyDto bookCopyDto2;
    BookCopyDto bookCopyDto3;

    BookCopy bookCopy1;
    BookCopy bookCopy2;
    BookCopy bookCopy3;

    Book book1;
    Book book2;
    Book book3;

    @BeforeEach
    public void setUp() {
        bookCopy1.setId(1L);
        bookCopy1.setBarcode(12345);
        bookCopy1.setAudioBook(false);
        bookCopy1.setInWrittenForm(true);
        bookCopy1.setDyslexiaFriendly(true);
        bookCopy1.setFormat("hardcover");
        bookCopy1.setNumberOfPages(30);
        bookCopy1.setTotalWordCount(300);
        bookCopy1.setYearPublished(LocalDate.of(2020, 01, 01));
        bookCopy1.setBook(book1);

        bookCopy2.setId(2L);
        bookCopy2.setBarcode(23456);
        bookCopy2.setAudioBook(false);
        bookCopy2.setInWrittenForm(true);
        bookCopy2.setDyslexiaFriendly(false);
        bookCopy2.setFormat("paperback");
        bookCopy2.setNumberOfPages(80);
        bookCopy2.setTotalWordCount(8000);
        bookCopy2.setYearPublished(LocalDate.of(2021, 01, 01));
        bookCopy2.setBook(book2);

        bookCopy3.setId(3L);
        bookCopy3.setBarcode(34567);
        bookCopy3.setAudioBook(false);
        bookCopy3.setInWrittenForm(true);
        bookCopy3.setDyslexiaFriendly(false);
        bookCopy3.setFormat("hardcover");
        bookCopy3.setNumberOfPages(28);
        bookCopy3.setTotalWordCount(100);
        bookCopy3.setYearPublished(LocalDate.of(2022, 01, 01));
        bookCopy3.setBook(book3);
    }

    @Test
    void createBookCopy() {
    }

    @Test
    void getOneCopy() {
    }

    @Test
    void getAllBookCopies() {
    }

    @Test
    void getAllBookCopiesPublishedAfter() {
    }

    @Test
    void getAllBookCopiesDyslexiaFriendly() {
    }

    @Test
    void getAllBookCopiesAudio() {
    }

    @Test
    void fullUpdateBookCopy() {
    }

    @Test
    void assignBookToBookCopy() {
    }

    @Test
    void deleteOneCopy() {
    }
}