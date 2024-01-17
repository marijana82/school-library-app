package com.marijana.library1223.services;

import com.marijana.library1223.models.Book;
import com.marijana.library1223.repositories.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    @Captor
    ArgumentCaptor<Book> captor;



    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    //tests

    @Test
    @Disabled
    void createNewBook() {
    }

    @Test
    @Disabled
    void showOneBook() {
    }

    @Test
    @Disabled
    void showAllBooks() {
    }

    //start with this test
    @Test
    @Disabled
    void showAllBooksByNameAuthor() {

        //Arrange
            //error: expected 0 arguments but found 6
            // Book book = new Book(98765, "Kleine onderzoekers voertuigen","Ruth Martin", "Ruth Martin", 4, "adventure");
        //Act
        //Assert
    }

    @Test
    @Disabled
    void showAllBooksByNameIllustrator() {
    }

    @Test
    @Disabled
    void showAllBooksByNameIllustratorAndNameAuthor() {
    }

    @Test
    @Disabled
    void showAllBooksByTopic() {
    }

    @Test
    @Disabled
    void deleteById() {
    }

    @Test
    @Disabled
    void updateOneBook() {
    }

    @Test
    @Disabled
    void updateBookPartially() {
    }

    @Test
    @Disabled
    void transferBookToBookDto() {
    }

    @Test
    @Disabled
    void transferBookDtoToBook() {
    }

    @Test
    @Disabled
    void transferBookToInformationBookDto() {
    }
}