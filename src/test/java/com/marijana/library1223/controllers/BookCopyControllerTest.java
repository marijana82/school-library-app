package com.marijana.library1223.controllers;

import com.marijana.library1223.services.BookCopyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookCopyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookCopyService bookCopyService;


    @BeforeEach
    void setUp() {
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