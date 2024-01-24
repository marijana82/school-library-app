package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.BookCopyDto;
import com.marijana.library1223.dtos.BookDto;
import com.marijana.library1223.models.Book;
import com.marijana.library1223.models.BookCopy;
import com.marijana.library1223.models.FileDocument;
import com.marijana.library1223.repositories.BookCopyRepository;
import com.marijana.library1223.repositories.BookRepository;
import com.marijana.library1223.repositories.FileUploadRepository;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
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
    Book book1;
    Book book2;
    Book book3;
    BookDto bookDto1;
    BookDto bookDto2;
    BookDto bookDto3;

    @BeforeEach
    public void setUp() {

        //file for upload
        file1 = new FileDocument();
        file2 = new FileDocument();
        file3 = new FileDocument();

        file1.setFileName("onderzoekers.jpeg");
        file2.setFileName("toon.jpeg");
        file3.setFileName("matilda.jpeg");

        file1 = fileUploadRepository.save(file1);
        file2 = fileUploadRepository.save(file2);
        file3 = fileUploadRepository.save(file3);

        //book
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

        //books saved in repository
        book1 = bookRepository.save(book1);
        book2 = bookRepository.save(book2);
        book3 = bookRepository.save(book3);

        //bookDto
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


        //book copy
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

        //book copy saved in repository
        bookCopy1 = bookCopyRepository.save(bookCopy1);
        bookCopy2 = bookCopyRepository.save(bookCopy2);
        bookCopy3 = bookCopyRepository.save(bookCopy3);


        //bookCopyDto
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
    //@Disabled
    //@WithMockUser(username="testuser", roles="USER")
    void getAllBookCopies() throws Exception {
        given(bookCopyService.getAllBookCopies()).willReturn(List.of(bookCopyDto1, bookCopyDto2, bookCopyDto3));

        mockMvc.perform(get("/book-copy"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                //book-copy 1
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].barcode").value(102345))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].audioBook").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].inWrittenForm").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dyslexiaFriendly").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].format").value("hardcover"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].numberOfPages").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalWordCount").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].yearPublished").value("1991-01-01"))
                    //book 1
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.id").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.isbn").value(12345))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.bookTitle").value("Kleine onderzoekers voertuigen"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.nameAuthor").value("Ruth Martin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.nameIllustrator").value("Ruth Martin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.suitableAge").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.bookPhoto").value(file1))
                //book-copy 2
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].barcode").value(80765))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].audioBook").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].inWrittenForm").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dyslexiaFriendly").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].format").value("paperback"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].numberOfPages").value(100))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].totalWordCount").value(20000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].yearPublished").value("2000-01-01"))
                     //book 2
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.id").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.isbn").value(123456))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.bookTitle").value("Langzaam, zosnel als zij konden"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.nameAuthor").value("Toon Tellegen"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.nameIllustrator").value("Mance Post"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.suitableAge").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookDto.bookPhoto").value(file2))
                //book-copy 3
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(1015))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].barcode").value(30276))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].audioBook").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].inWrittenForm").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].dyslexiaFriendly").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].format").value("paperback"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].numberOfPages").value(176))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].totalWordCount").value(50000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].yearPublished").value("2015-02-02"))
                //book 3
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bookDto.id").value(1015))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bookDto.isbn").value(678901))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bookDto.bookTitle").value("Matilda"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bookDto.nameAuthor").value("Roald Dahl"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bookDto.nameIllustrator").value("Quentin Blake"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bookDto.suitableAge").value(8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bookDto.bookPhoto").value(file3));
    }


    @Test
    //@Disabled
        //@WithMockUser(username="testuser", roles="USER")

    void getAllBookCopiesPublishedAfter() throws Exception {

        given(bookCopyService.getAllBookCopiesPublishedAfter(LocalDate.of(2015, 01, 01))).willReturn(List.of(bookCopyDto3));


        mockMvc.perform(get("/book-copy/after?date=2015-01-01"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())

                //book-copy 3
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1015))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].barcode").value(30276))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].audioBook").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].inWrittenForm").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dyslexiaFriendly").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].format").value("paperback"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].numberOfPages").value(176))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalWordCount").value(50000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].yearPublished").value("2015-02-02"))
                //book 3
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.id").value(1015))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.isbn").value(678901))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.bookTitle").value("Matilda"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.nameAuthor").value("Roald Dahl"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.nameIllustrator").value("Quentin Blake"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.suitableAge").value(8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookDto.bookPhoto").value(file3));

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