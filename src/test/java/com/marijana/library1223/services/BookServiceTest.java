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
    Book book4;
    ReadingBook readingBook1;
    InformationBook informationBook1;
    InformationBook informationBook2;
    InformationBook informationBook3;
    BookDto bookDto1;
    BookDto bookDto2;
    BookDto bookDto3;
    BookDto bookDto4;
    InformationBookDto informationBookDto1;



    //load general test-data before the start of each test
    @BeforeEach
    void setUp() {

        //set-up data for information book
        informationBook1 = new InformationBook("adventure", "beginners");
        informationBook2 = new InformationBook("humor", "beginners");
        informationBook3 = new InformationBook("fantasy", "beginners");

        //set-up data for reading book
        readingBook1 = new ReadingBook("dutch", "adventure", "basic");


        //set-up data for book
        book1 = new Book(1000L, 98765, "Kleine onderzoekers voertuigen", "Ruth Martin", "Ruth Martin", 4);
        book2 = new Book(1001L, 8765, "Graafmachines en kiepautos", "Angela Royston", "David Barrow", 4);
        book3 = new Book(1002L, 763987, "Barbapapa", "Annette Tilson", "Talus Taylor", 5 );
        book4 = new Book(1003L, 62983, "Woeste Willem", "Ingrid Schubert", "Dieter Schubert", 5, informationBook1);

    }

    //clean up the database
    @AfterEach
    void tearDown() {
    }

    //tests
    @Test
    @DisplayName("Should create new book")
    void createNewBook() {
        //Arrange
        when(bookRepository.save(book1)).thenReturn(book1);

        //Act
        bookService.createNewBook(bookDto1);
        verify(bookRepository, times(1)).save(captor.capture());
        Book bookSaving = captor.getValue();

        //Assert for book
        assertEquals(book1.getId(), bookSaving.getId());
        assertEquals(book1.getIsbn(), bookSaving.getIsbn());
        assertEquals(book1.getNameAuthor(), bookSaving.getNameAuthor());
        assertEquals(book1.getBookTitle(), bookSaving.getBookTitle());
        assertEquals(book1.getNameIllustrator(), bookSaving.getNameIllustrator());
        assertEquals(book1.getSuitableAge(), bookSaving.getSuitableAge());

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

    //TODO: CHECK WHY IS THIS NOT WORKING (Book object stays null)
    @Test
    @DisplayName("Should show all books by topic")
    @Disabled
    void showAllBooksByTopic() {
        //Arrange
        when(bookRepository.existsById(1003L)).thenReturn(true);
        when(bookRepository.findAllBooksByCurrentTopic(informationBook1.getCurrentTopic())).thenReturn(List.of(book4));

        //Act
        List<Book> bookList = bookRepository.findAllBooksByCurrentTopic("humor");
        List<InformationBookDto> bookDtoList = bookService.showAllBooksByTopic(bookList.get(0).getInformationBook().getCurrentTopic());
        //Assert
        assertEquals(book4.getInformationBook().getCurrentTopic(), bookDtoList);

        /*assertNotNull(bookDtoList);
        assertFalse(bookDtoList.isEmpty());

        InformationBookDto informationBookDto = bookDtoList.get(0);
        if (book4.getInformationBook() != null) {
            //ERROR: Cannot invoke "com.marijana.library1223.models.InformationBook.getCurrentTopic()" because the return value of "com.marijana.library1223.models.Book.getInformationBook()" is null
            assertEquals(book4.getInformationBook().getCurrentTopic(), informationBookDto.getCurrentTopic());
        } else {
            assertNull(informationBookDto.getCurrentTopic());
        }*/
    }

    @Test
    @DisplayName("Should delete by id")
    void deleteById() {
        when(bookRepository.existsById(1000L)).thenReturn(true);
        when(bookRepository.findById(1000L)).thenReturn(Optional.of(book1));
        bookService.deleteById(1000L);
        verify(bookRepository).delete(book1);
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