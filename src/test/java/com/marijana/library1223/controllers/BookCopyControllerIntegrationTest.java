package com.marijana.library1223.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.marijana.library1223.dtos.BookCopyDto;
import com.marijana.library1223.dtos.BookDto;
import com.marijana.library1223.models.Book;
import com.marijana.library1223.models.BookCopy;
import com.marijana.library1223.models.FileDocument;
import com.marijana.library1223.repositories.BookCopyRepository;
import com.marijana.library1223.repositories.BookRepository;
import com.marijana.library1223.repositories.FileUploadRepository;
import com.marijana.library1223.services.BookCopyService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class BookCopyControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookCopyService bookCopyService;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    FileUploadRepository fileUploadRepository;



    FileDocument file1;
    FileDocument file2;
    FileDocument file3;
    BookCopy bookCopy1;
    BookCopy bookCopy2;
    BookCopy bookCopy3;
    BookCopyDto bookCopyDto1;
    BookCopyDto bookCopyDto2;
    BookCopyDto bookCopyDto3;
    BookCopyDto bookCopyUpdate;
    Book book1;
    Book book2;
    Book book3;
    BookDto bookDto1;
    BookDto bookDto2;
    BookDto bookDto3;
    BookDto bookDto4;


    @BeforeEach
    public void setUp() {

       /* if(fileUploadRepository.count() > 0) {
            fileUploadRepository.deleteAll();
        }

        if(bookRepository.count() > 0) {
            bookRepository.deleteAll();
        }


        if(bookCopyRepository.count() > 0 ) {
            bookCopyRepository.deleteAll();
        }*/

        file1 = new FileDocument();
        file2 = new FileDocument();
        file3 = new FileDocument();

        file1.setFileName("onderzoekers.jpeg");
        file2.setFileName("toon.jpeg");
        file3.setFileName("matilda.jpeg");

        file1 = fileUploadRepository.save(file1);
        file2 = fileUploadRepository.save(file2);
        file3 = fileUploadRepository.save(file3);

        book1 = new Book();
        book1.setId(1000L);
        book1.setIsbn(12345);
        book1.setNameAuthor("Ruth Martin");
        book1.setNameIllustrator("Ruth Martin");
        book1.setBookTitle("Kleine onderzoekers voertuigen");
        book1.setSuitableAge(4);
        book1.setBookPhoto(file1);

        book2 = new Book();
        book2.setId(1010L);
        book2.setIsbn(123456);
        book2.setNameAuthor("Toon Tellegen");
        book2.setNameIllustrator("Mance Post");
        book2.setBookTitle("Langzaam, zosnel als zij konden");
        book2.setSuitableAge(7);
        book2.setBookPhoto(file2);

        book3 = new Book();
        book3.setId(1015L);
        book3.setIsbn(678901);
        book3.setNameAuthor("Roald Dahl");
        book3.setNameIllustrator("Quentin Blake");
        book3.setBookTitle("Matilda");
        book3.setSuitableAge(8);
        book3.setBookPhoto(file3);

        book1 = bookRepository.save(book1);
        book2 = bookRepository.save(book2);
        book3 = bookRepository.save(book3);

        bookDto1 = new BookDto();
        bookDto1.setId(1000L);
        bookDto1.setIsbn(12345);
        bookDto1.setNameAuthor("Ruth Martin");
        bookDto1.setNameIllustrator("Ruth Martin");
        bookDto1.setBookTitle("Kleine onderzoekers voertuigen");
        bookDto1.setSuitableAge(4);
        bookDto1.setBookPhoto(file1);

        bookDto2 = new BookDto();
        bookDto2.setId(1010L);
        bookDto2.setIsbn(123456);
        bookDto2.setBookTitle("Langzaam, zosnel als zij konden");
        bookDto2.setNameAuthor("Toon Tellegen");
        bookDto2.setNameIllustrator("Mance Post");
        bookDto2.setSuitableAge(7);
        bookDto2.setBookPhoto(file2);

        bookDto3 = new BookDto();
        bookDto3.setId(1015L);
        bookDto3.setIsbn(678901);
        bookDto3.setBookTitle("Matilda");
        bookDto3.setNameAuthor("Roald Dahl");
        bookDto3.setNameIllustrator("Quentin Blake");
        bookDto3.setSuitableAge(8);
        bookDto3.setBookPhoto(file3);

        bookDto4 = new BookDto();
        bookDto4.setId(1015L);
        bookDto4.setIsbn(789016);
        bookDto4.setBookTitle("Matilda");
        bookDto4.setNameAuthor("Roald Dahl");
        bookDto4.setNameIllustrator("Quentin Blake");
        bookDto4.setSuitableAge(9);
        bookDto4.setBookPhoto(file3);

        bookCopy1 = new BookCopy();
        bookCopy1.setId(1000L);
        bookCopy1.setBarcode(102345);
        bookCopy1.setAudioBook(false);
        bookCopy1.setInWrittenForm(true);
        bookCopy1.setDyslexiaFriendly(false);
        bookCopy1.setFormat("hardcover");
        bookCopy1.setNumberOfPages(20);
        bookCopy1.setTotalWordCount(200);
        bookCopy1.setYearPublished(LocalDate.of(1991, 01, 01));
        bookCopy1.setBook(book1);

        bookCopy2  = new BookCopy();
        bookCopy2.setId(1010L);
        bookCopy2.setBarcode(80765);
        bookCopy2.setAudioBook(true);
        bookCopy2.setInWrittenForm(true);
        bookCopy2.setDyslexiaFriendly(false);
        bookCopy2.setFormat("paperback");
        bookCopy2.setNumberOfPages(100);
        bookCopy2.setTotalWordCount(20000);
        bookCopy2.setYearPublished(LocalDate.of(2000, 01, 01));
        bookCopy2.setBook(book2);

        bookCopy3 = new BookCopy();
        bookCopy3.setId(1015L);
        bookCopy3.setBarcode(30276);
        bookCopy3.setAudioBook(true);
        bookCopy3.setInWrittenForm(true);
        bookCopy3.setDyslexiaFriendly(false);
        bookCopy3.setFormat("paperback");
        bookCopy3.setNumberOfPages(176);
        bookCopy3.setTotalWordCount(50000);
        bookCopy3.setYearPublished(LocalDate.of(2015, 02, 02));
        bookCopy3.setBook(book3);

        bookCopy1 = bookCopyRepository.save(bookCopy1);
        bookCopy2 = bookCopyRepository.save(bookCopy2);
        bookCopy3 = bookCopyRepository.save(bookCopy3);

        bookCopyDto1 = new BookCopyDto();
        bookCopyDto1.setId(1000L);
        bookCopyDto1.setBarcode(102345);
        bookCopyDto1.setAudioBook(false);
        bookCopyDto1.setInWrittenForm(true);
        bookCopyDto1.setDyslexiaFriendly(false);
        bookCopyDto1.setFormat("hardcover");
        bookCopyDto1.setNumberOfPages(20);
        bookCopyDto1.setTotalWordCount(200);
        bookCopyDto1.setYearPublished(LocalDate.of(1991, 01, 01));
        bookCopyDto1.setBookDto(bookDto1);

        bookCopyDto2 = new BookCopyDto();
        bookCopyDto2.setId(1010L);
        bookCopyDto2.setBarcode(80765);
        bookCopyDto2.setAudioBook(true);
        bookCopyDto2.setInWrittenForm(true);
        bookCopyDto2.setDyslexiaFriendly(false);
        bookCopyDto2.setFormat("paperback");
        bookCopyDto2.setNumberOfPages(100);
        bookCopyDto2.setTotalWordCount(20000);
        bookCopyDto2.setYearPublished(LocalDate.of(2000, 01, 01));
        bookCopyDto2.setBookDto(bookDto2);

        bookCopyDto3 = new BookCopyDto();
        bookCopyDto3.setId(1015L);
        bookCopyDto3.setBarcode(30276);
        bookCopyDto3.setAudioBook(true);
        bookCopyDto3.setInWrittenForm(true);
        bookCopyDto3.setDyslexiaFriendly(false);
        bookCopyDto3.setFormat("paperback");
        bookCopyDto3.setNumberOfPages(176);
        bookCopyDto3.setTotalWordCount(50000);
        bookCopyDto3.setYearPublished(LocalDate.of(2015, 02, 02));
        bookCopyDto3.setBookDto(bookDto3);

        bookCopyUpdate = new BookCopyDto();
        bookCopyUpdate.setId(1015L);
        bookCopyUpdate.setBarcode(40276);
        bookCopyUpdate.setAudioBook(true);
        bookCopyUpdate.setInWrittenForm(true);
        bookCopyUpdate.setDyslexiaFriendly(true);
        bookCopyUpdate.setFormat("hardcover");
        bookCopyUpdate.setNumberOfPages(200);
        bookCopyUpdate.setTotalWordCount(50000);
        bookCopyUpdate.setYearPublished(LocalDate.of(2016, 02, 02));
        bookCopyUpdate.setBookDto(bookDto4);

    }

    @Test
    @DisplayName("Should create book copy")
    void createBookCopy() throws Exception {

        given(bookCopyService.createBookCopy(bookCopyDto1)).willReturn(bookCopyDto1);

        mockMvc.perform(MockMvcRequestBuilders.post("/book-copy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bookCopyDto1)))
                .andExpect(status().isCreated())

                .andExpect(MockMvcResultMatchers.jsonPath("id").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("barcode").value(102345))
                .andExpect(MockMvcResultMatchers.jsonPath("audioBook").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("inWrittenForm").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("dyslexiaFriendly").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("format").value("hardcover"))
                .andExpect(MockMvcResultMatchers.jsonPath("numberOfPages").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("totalWordCount").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("yearPublished").value("1991-01-01"))

                .andExpect(MockMvcResultMatchers.jsonPath("bookDto.id").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("bookDto.isbn").value(12345))
                .andExpect(MockMvcResultMatchers.jsonPath("bookDto.bookTitle").value("Kleine onderzoekers voertuigen"))
                .andExpect(MockMvcResultMatchers.jsonPath("bookDto.nameAuthor").value("Ruth Martin"))
                .andExpect(MockMvcResultMatchers.jsonPath("bookDto.nameIllustrator").value("Ruth Martin"))
                .andExpect(MockMvcResultMatchers.jsonPath("bookDto.suitableAge").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("bookDto.bookPhoto").value(file1));
    }

    @Test
    @DisplayName("Should create book copy with binding result errors")
    void createBookCopyWithBindingResultErrors() throws Exception {
        BookCopyDto errorBookCopyDto = new BookCopyDto();
        errorBookCopyDto.setFormat("error");

        mockMvc.perform(post("/book-copy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(errorBookCopyDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(containsString("barcode : must be greater than or equal to 4")));
    }



    @Test
    @DisplayName("Should get one copy")
    void getOneCopy() throws Exception {

        given(bookCopyService.showOneCopy(1000L)).willReturn(bookCopyDto1);

        mockMvc.perform(get("/book-copy/1000"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())

                .andExpect(MockMvcResultMatchers.jsonPath("id").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("barcode").value(102345))
                .andExpect(MockMvcResultMatchers.jsonPath("audioBook").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("inWrittenForm").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("dyslexiaFriendly").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("format").value("hardcover"))
                .andExpect(MockMvcResultMatchers.jsonPath("numberOfPages").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("totalWordCount").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("yearPublished").value("1991-01-01"))

                .andExpect(MockMvcResultMatchers.jsonPath("bookDto.id").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("bookDto.isbn").value(12345))
                .andExpect(MockMvcResultMatchers.jsonPath("bookDto.bookTitle").value("Kleine onderzoekers voertuigen"))
                .andExpect(MockMvcResultMatchers.jsonPath("bookDto.nameAuthor").value("Ruth Martin"))
                .andExpect(MockMvcResultMatchers.jsonPath("bookDto.nameIllustrator").value("Ruth Martin"))
                .andExpect(MockMvcResultMatchers.jsonPath("bookDto.suitableAge").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("bookDto.bookPhoto").value(file1));
    }


    @Test
    @DisplayName("Should get all book copies")
    void getAllBookCopies() throws Exception {
        given(bookCopyService.getAllBookCopies()).willReturn(List.of(bookCopyDto1, bookCopyDto2, bookCopyDto3));

        mockMvc.perform(get("/book-copy"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].barcode").value(102345))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].audioBook").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].inWrittenForm").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dyslexiaFriendly").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].format").value("hardcover"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].numberOfPages").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalWordCount").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].yearPublished").value("1991-01-01"))

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.id").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.isbn").value(12345))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.bookTitle").value("Kleine onderzoekers voertuigen"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.nameAuthor").value("Ruth Martin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.nameIllustrator").value("Ruth Martin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.suitableAge").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.bookPhoto").value(file1))

                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].barcode").value(80765))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].audioBook").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].inWrittenForm").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dyslexiaFriendly").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].format").value("paperback"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].numberOfPages").value(100))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].totalWordCount").value(20000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].yearPublished").value("2000-01-01"))

                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.id").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.isbn").value(123456))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.bookTitle").value("Langzaam, zosnel als zij konden"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.nameAuthor").value("Toon Tellegen"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.nameIllustrator").value("Mance Post"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.suitableAge").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.bookPhoto").value(file2))

                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(1015))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].barcode").value(30276))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].audioBook").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].inWrittenForm").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].dyslexiaFriendly").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].format").value("paperback"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].numberOfPages").value(176))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].totalWordCount").value(50000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].yearPublished").value("2015-02-02"))

                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bookDto.id").value(1015))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bookDto.isbn").value(678901))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bookDto.bookTitle").value("Matilda"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bookDto.nameAuthor").value("Roald Dahl"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bookDto.nameIllustrator").value("Quentin Blake"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bookDto.suitableAge").value(8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bookDto.bookPhoto").value(file3));
    }


    @Test
    @DisplayName("Should get all book copies published after")
    void getAllBookCopiesPublishedAfter() throws Exception {

        given(bookCopyService.getAllBookCopiesPublishedAfter(LocalDate.of(2015, 01, 01))).willReturn(List.of(bookCopyDto3));


        mockMvc.perform(get("/book-copy/after?date=2015-01-01"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1015))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].barcode").value(30276))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].audioBook").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].inWrittenForm").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dyslexiaFriendly").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].format").value("paperback"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].numberOfPages").value(176))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalWordCount").value(50000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].yearPublished").value("2015-02-02"))

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.id").value(1015))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.isbn").value(678901))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.bookTitle").value("Matilda"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.nameAuthor").value("Roald Dahl"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.nameIllustrator").value("Quentin Blake"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.suitableAge").value(8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.bookPhoto").value(file3));
    }

    @Test
    @DisplayName("Should get all book copies dyslexia friendly")
    void getAllBookCopiesDyslexiaFriendly() throws Exception {

        given(bookCopyService.getAllBookCopiesDyslexiaFriendly(false)).willReturn(List.of(bookCopyDto1, bookCopyDto2, bookCopyDto3));

        mockMvc.perform(get("/book-copy/dyslexia?dyslexia=false"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].barcode").value(102345))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].audioBook").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].inWrittenForm").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dyslexiaFriendly").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].format").value("hardcover"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].numberOfPages").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalWordCount").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].yearPublished").value("1991-01-01"))

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.id").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.isbn").value(12345))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.bookTitle").value("Kleine onderzoekers voertuigen"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.nameAuthor").value("Ruth Martin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.nameIllustrator").value("Ruth Martin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.suitableAge").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.bookPhoto").value(file1))

                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].barcode").value(80765))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].audioBook").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].inWrittenForm").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dyslexiaFriendly").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].format").value("paperback"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].numberOfPages").value(100))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].totalWordCount").value(20000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].yearPublished").value("2000-01-01"))

                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.id").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.isbn").value(123456))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.bookTitle").value("Langzaam, zosnel als zij konden"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.nameAuthor").value("Toon Tellegen"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.nameIllustrator").value("Mance Post"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.suitableAge").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.bookPhoto").value(file2))

                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(1015))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].barcode").value(30276))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].audioBook").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].inWrittenForm").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].dyslexiaFriendly").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].format").value("paperback"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].numberOfPages").value(176))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].totalWordCount").value(50000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].yearPublished").value("2015-02-02"))

                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bookDto.id").value(1015))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bookDto.isbn").value(678901))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bookDto.bookTitle").value("Matilda"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bookDto.nameAuthor").value("Roald Dahl"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bookDto.nameIllustrator").value("Quentin Blake"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bookDto.suitableAge").value(8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bookDto.bookPhoto").value(file3));
    }

    @Test
    @DisplayName("Should get all book copies audio")
    void getAllBookCopiesAudio() throws Exception {

        given(bookCopyService.getAllBookCopiesAudio(true)).willReturn(List.of(bookCopyDto2, bookCopyDto3));

        mockMvc.perform(get("/book-copy/audio?audio=true"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].barcode").value(80765))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].audioBook").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].inWrittenForm").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dyslexiaFriendly").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].format").value("paperback"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].numberOfPages").value(100))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalWordCount").value(20000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].yearPublished").value("2000-01-01"))

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.id").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.isbn").value(123456))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.bookTitle").value("Langzaam, zosnel als zij konden"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.nameAuthor").value("Toon Tellegen"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.nameIllustrator").value("Mance Post"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.suitableAge").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.bookPhoto").value(file2))

                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(1015))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].barcode").value(30276))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].audioBook").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].inWrittenForm").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dyslexiaFriendly").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].format").value("paperback"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].numberOfPages").value(176))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].totalWordCount").value(50000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].yearPublished").value("2015-02-02"))

                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.id").value(1015))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.isbn").value(678901))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.bookTitle").value("Matilda"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.nameAuthor").value("Roald Dahl"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.nameIllustrator").value("Quentin Blake"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.suitableAge").value(8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.bookPhoto").value(file3));
    }

    @Test
    @DisplayName("Should do full update book copy")
    void fullUpdateBookCopy() throws Exception {

        given(bookCopyService.updateOneBookCopy(1015L, bookCopyDto3)).willReturn(bookCopyUpdate);

        mockMvc.perform(put("/book-copy/1015")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(bookCopyDto3)))
                .andExpect(status().isOk())

                .andExpect(MockMvcResultMatchers.jsonPath("id").value(1015))
                .andExpect(MockMvcResultMatchers.jsonPath("barcode").value(40276))
                .andExpect(MockMvcResultMatchers.jsonPath("audioBook").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("inWrittenForm").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("dyslexiaFriendly").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("format").value("hardcover"))
                .andExpect(MockMvcResultMatchers.jsonPath("numberOfPages").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("totalWordCount").value(50000))
                .andExpect(MockMvcResultMatchers.jsonPath("yearPublished").value("2016-02-02"))

                .andExpect(MockMvcResultMatchers.jsonPath("bookDto.id").value(1015))
                .andExpect(MockMvcResultMatchers.jsonPath("bookDto.isbn").value(789016))
                .andExpect(MockMvcResultMatchers.jsonPath("bookDto.bookTitle").value("Matilda"))
                .andExpect(MockMvcResultMatchers.jsonPath("bookDto.nameAuthor").value("Roald Dahl"))
                .andExpect(MockMvcResultMatchers.jsonPath("bookDto.nameIllustrator").value("Quentin Blake"))
                .andExpect(MockMvcResultMatchers.jsonPath("bookDto.suitableAge").value(9))
                .andExpect(MockMvcResultMatchers.jsonPath("bookDto.bookPhoto").value(file3));
    }

    @Test
    @DisplayName("Should assign book to book copy")
    void assignBookToBookCopy() throws Exception {

        willDoNothing().given(bookCopyService).assignBookToBookCopy(1000L, 1000L);

        mockMvc.perform(put("/book-copy/1000/books/1000")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(bookCopyDto1)))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Should delete one copy")
    void deleteOneCopy() throws Exception {
        mockMvc.perform(delete("/book-copy/1000")).andExpect(status().isNoContent());
    }


    //(de)serialization of json.........
    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}