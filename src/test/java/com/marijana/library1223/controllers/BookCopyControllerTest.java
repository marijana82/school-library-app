package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.BookCopyDto;
import com.marijana.library1223.dtos.BookDto;
import com.marijana.library1223.models.Book;
import com.marijana.library1223.models.BookCopy;
import com.marijana.library1223.models.FileDocument;
import com.marijana.library1223.repositories.BookCopyRepository;
import com.marijana.library1223.repositories.BookRepository;
import com.marijana.library1223.services.BookCopyService;
import com.marijana.library1223.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookCopyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookCopyService bookCopyService;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    FileDocument file1;
    FileDocument file2;
    FileDocument file3;
    BookCopy bookCopy1;
    BookCopy bookCopy2;
    BookCopy bookCopy3;
    BookCopyDto bookCopyDto1;
    BookCopyDto bookCopyDto2;
    BookCopyDto bookCopyDto3;
    Book book1;
    Book book2;
    Book book3;
    BookDto bookDto1;
    BookDto bookDto2;
    BookDto bookDto3;

    @BeforeEach
    public void setUp() {

        //file for upload
        file1.setFileName("bookPhoto1");
        file2.setFileName("bookPhoto2");
        file3.setFileName("bookPhoto3");

        //book
        book1.setId(1L);
        book1.setIsbn(12345);
        book1.setNameAuthor("Author 1");
        book1.setNameIllustrator("Illustrator 1");
        book1.setBookTitle("Book 1");
        book1.setSuitableAge(6);
        book1.setBookPhoto(file1);

        book2.setId(2L);
        book2.setIsbn(23456);
        book2.setNameAuthor("Author 2");
        book2.setNameIllustrator("Illustrator 2");
        book2.setBookTitle("Book 2");
        book2.setSuitableAge(10);
        book2.setBookPhoto(file2);

        book3.setId(3L);
        book3.setIsbn(34567);
        book3.setNameAuthor("Author 3");
        book3.setNameIllustrator("Illustrator 3");
        book3.setBookTitle("Book 3");
        book3.setSuitableAge(5);
        book3.setBookPhoto(file3);

        //books saved in repository
        book1 = bookRepository.save(book1);
        book2 = bookRepository.save(book2);
        book3 = bookRepository.save(book3);

        bookDto1.setId(1L);
        bookDto1.setIsbn(12345);
        bookDto1.setNameAuthor("Author1");
        bookDto1.setNameIllustrator("Illustrator1");
        bookDto1.setBookTitle("Book1");
        bookDto1.setSuitableAge(6);
        bookDto1.setBookPhoto(file1);

        //book copy
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

        bookCopy1 = bookCopyRepository.save(bookCopy1);
        bookCopy2 = bookCopyRepository.save(bookCopy2);
        bookCopy3 = bookCopyRepository.save(bookCopy3);

        bookDto1.setId(1L);
        bookDto1.setIsbn(12345);
        bookDto1.setBookTitle("Book1");
        bookDto1.setNameAuthor("Author1");
        bookDto1.setNameIllustrator("Illustrator1");
        bookDto1.setSuitableAge(6);
        bookDto1.setBookPhoto(file1);

        bookDto2.setId(2L);
        bookDto2.setIsbn(23456);
        bookDto2.setBookTitle("Book2");
        bookDto2.setNameAuthor("Author2");
        bookDto2.setNameIllustrator("Illustrator2");
        bookDto2.setSuitableAge(10);
        bookDto2.setBookPhoto(file2);

        bookDto3.setId(3L);
        bookDto3.setIsbn(34567);
        bookDto3.setBookTitle("Book3");
        bookDto3.setNameAuthor("Author3");
        bookDto3.setNameIllustrator("Illustrator3");
        bookDto3.setSuitableAge(5);
        bookDto3.setBookPhoto(file3);

    }

    @Test
    @Disabled
    void createBookCopy() {
    }

    @Test
    @Disabled
    void getOneCopy() {
    }

    //start here
    @Test
    void getAllBookCopies() throws Exception {
        given(bookCopyService.getAllBookCopies()).willReturn(List.of(bookCopyDto1, bookCopyDto2, bookCopyDto3));
        mockMvc.perform(get("/book-copy"))
                .andExpect(status().isOk())
                //book-copy 1
                .andExpect((ResultMatcher) jsonPath("$[0].id").value(1))
                .andExpect((ResultMatcher) jsonPath("$[0].barcode").value(12345))
                .andExpect((ResultMatcher) jsonPath("$[0].audioBook").value(false))
                .andExpect((ResultMatcher) jsonPath("$[0].inWrittenForm").value(true))
                .andExpect((ResultMatcher) jsonPath("$[0].dyslexiaFriendly").value(true))
                .andExpect((ResultMatcher) jsonPath("$[0].format").value("hardcover"))
                .andExpect((ResultMatcher) jsonPath("$[0].numberOfPages").value(30))
                .andExpect((ResultMatcher) jsonPath("$[0].totalWordCount").value(300))
                .andExpect((ResultMatcher) jsonPath("$[0].yearPublished").value(LocalDate.of(2020, 01, 01)))
                    //book 1
                .andExpect((ResultMatcher) jsonPath("$[0].bookDto1.id").value(1))
                .andExpect((ResultMatcher) jsonPath("$[0].bookDto1.isbn").value(12345))
                .andExpect((ResultMatcher) jsonPath("$[0].bookDto1.bookTitle").value("Book1"))
                .andExpect((ResultMatcher) jsonPath("$[0].bookDto.nameAuthor").value("Author1"))
                .andExpect((ResultMatcher) jsonPath("$[0].bookDto.nameIllustrator").value("Illustrator1"))
                .andExpect((ResultMatcher) jsonPath("$[0].bookDto.suitableAge").value(6))
                .andExpect((ResultMatcher) jsonPath("$[0].bookDto.bookPhoto").value(file1));
    }

    @Test
    @Disabled
    void getAllBookCopiesPublishedAfter() {
    }

    @Test
    @Disabled
    void getAllBookCopiesDyslexiaFriendly() {
    }

    @Test
    @Disabled
    void getAllBookCopiesAudio() {
    }

    @Test
    @Disabled
    void fullUpdateBookCopy() {
    }

    @Test
    @Disabled
    void assignBookToBookCopy() {
    }

    @Test
    @Disabled
    void deleteOneCopy() {
    }
}