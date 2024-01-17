package com.marijana.library1223.services;

import com.marijana.library1223.dtos.BookDto;
import com.marijana.library1223.dtos.InformationBookDto;
import com.marijana.library1223.models.Book;
import com.marijana.library1223.models.InformationBook;
import com.marijana.library1223.models.ReadingBook;
import com.marijana.library1223.repositories.BookRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @Mock
    ReadingBook readingBook;

    @Mock
    InformationBook informationBook;

    @InjectMocks
    BookService bookService;

    @Captor
    ArgumentCaptor<Book> captor;
    Book book1;
    Book book2;
    Book book3;
    ReadingBook readingBook1;
    InformationBook informationBook1;
    BookDto bookDto1;
    BookDto bookDto2;
    BookDto bookDto3;
    InformationBookDto informationBookDto;


    //to load general test-data before the start of each test
    @BeforeEach
    void setUp() {
        book1 = new Book(1000, 98765, "Kleine onderzoekers voertuigen", "Ruth Martin", "Ruth Martin", 4);
        book2 = new Book(1001, 8765, "Graafmachines en kiepautos", "Angela Royston", "David Barrow", 4);
    }

    //defining that the database gets cleaned up
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
    @DisplayName("Should show all books")
    //@Disabled
    void showAllBooks() {
        //Arrange
        when(bookRepository.findAll()).thenReturn(List.of(book1, book2));
        //Act
        List<Book> booksFound = bookRepository.findAll();
        //Assert
        assertEquals(book1.getBookTitle(), booksFound.get(0).getBookTitle());
        assertEquals(book2.getBookTitle(), booksFound.get(1).getBookTitle());
    }


    @Test
    @DisplayName("Should show all books by name author")
    @Disabled
    void showAllBooksByNameAuthor() {
        //Arrange

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