package com.marijana.library1223.services;

import com.marijana.library1223.dtos.BookDto;
import com.marijana.library1223.dtos.InformationBookDto;
import com.marijana.library1223.exceptions.RecordNotFoundException;
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
import java.util.Optional;

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
    String RecordNotFoundException;


    //to load general test-data before the start of each test
    @BeforeEach
    void setUp() {
        book1 = new Book(1000L, 98765, "Kleine onderzoekers voertuigen", "Ruth Martin", "Ruth Martin", 4);
        book2 = new Book(1001L, 8765, "Graafmachines en kiepautos", "Angela Royston", "David Barrow", 4);
        book3 = new Book(1002L, 763987, "Barbapapa", "Annette Tilson", "Talus Taylor", 5 );
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
    @DisplayName("Should show one book")
    void showOneBook() {
        //Arrange
        when(bookRepository.findById(1000L)).thenReturn(Optional.of(book1));
        //Act
        BookDto bookDto = bookService.showOneBook(1000L);
        //Assert
        assertEquals(book1.getBookTitle(), bookDto.getBookTitle());
        //Assert in case the book is not found
        assertThrows(RecordNotFoundException.class, () -> {
            bookService.showOneBook(null);
        });
    }

    @Test
    @DisplayName("Should show all books")
    void showAllBooks() {
        //Arrange
        when(bookRepository.findAll()).thenReturn(List.of(book1, book2));
        //Act
        List<BookDto> bookDtoList = bookService.showAllBooks();
        //Assert
        assertEquals(book1.getBookTitle(), bookDtoList.get(0).getBookTitle());
        assertEquals(book2.getBookTitle(), bookDtoList.get(1).getBookTitle());
    }


    @Test
    @DisplayName("Should show all books by name author")
    void showAllBooksByNameAuthor() {
        //Arrange
        when(bookRepository.findAllBooksByNameAuthorEqualsIgnoreCase("Ruth Martin")).thenReturn(List.of(book1));
        //Act
        List<BookDto> bookDtoList = bookService.showAllBooksByNameAuthor("Ruth Martin");
        //Assert
        assertEquals(book1.getNameAuthor(), bookDtoList.get(0).getNameAuthor());

    }

    @Test
    @DisplayName("Should show all books by name illustrator")
    void showAllBooksByNameIllustrator() {
        //Arrange
        when(bookRepository.findAllBooksByNameIllustratorEqualsIgnoreCase("David Barrow")).thenReturn(List.of(book2));
        //Act
        List<BookDto> bookDtoList = bookService.showAllBooksByNameIllustrator("David Barrow");
        //Assert
        assertEquals(book2.getNameIllustrator(), bookDtoList.get(0).getNameIllustrator());
    }

    @Test
    @DisplayName("Should show all books by name illustrator and name author")
    void showAllBooksByNameIllustratorAndNameAuthor() {
        //Arrange
        when(bookRepository.findAllBooksByNameIllustratorAndNameAuthorEqualsIgnoreCase("Talus Taylor", "Annette Tilson")).thenReturn(List.of(book3));
        //Act
        List<BookDto> bookDtoList = bookService.showAllBooksByNameIllustratorAndNameAuthor("Talus Taylor", "Annette Tilson");
        //Assert
        assertEquals(book3.getNameIllustrator(), bookDtoList.get(0).getNameIllustrator());
        assertEquals(book3.getNameAuthor(), bookDtoList.get(0).getNameAuthor());

    }

    @Test
    @DisplayName("Should show all books by topic")
    @Disabled
    void showAllBooksByTopic() {
        //Arrange
        //Act
        //Assert

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