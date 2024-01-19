package com.marijana.library1223.services;

import com.marijana.library1223.dtos.BookDto;
import com.marijana.library1223.dtos.InformationBookDto;
import com.marijana.library1223.exceptions.IdNotFoundException;
import com.marijana.library1223.exceptions.RecordNotFoundException;
import com.marijana.library1223.models.Book;
import com.marijana.library1223.models.InformationBook;
import com.marijana.library1223.models.ReadingBook;
import com.marijana.library1223.repositories.BookRepository;
import jakarta.persistence.Id;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
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

    Book book;
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
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setIsbn(12345);
        bookDto.setBookTitle("Mathilda");
        bookDto.setNameAuthor("Roald Dahl");
        bookDto.setNameIllustrator("Quentin Blake");
        bookDto.setSuitableAge(9);

        Book book = new Book();
        book.setId(bookDto.getId());
        book.setIsbn(bookDto.getIsbn());
        book.setBookTitle(bookDto.getBookTitle());
        book.setNameAuthor(bookDto.getNameAuthor());
        book.setNameIllustrator(bookDto.getNameIllustrator());
        book.setSuitableAge(bookDto.getSuitableAge());


        when(bookRepository.save(book)).thenReturn(book);
        //TODO: test information book and reading book

        BookDto bookDto1 = bookService.createNewBook(bookDto);

        //Act
        assertEquals(12345, bookDto1.getIsbn());
        assertEquals("Mathilda", bookDto1.getBookTitle());
        assertEquals("Roald Dahl", bookDto1.getNameAuthor());
        assertEquals("Quentin Blake", bookDto1.getNameIllustrator());
        assertEquals(9, bookDto1.getSuitableAge());


        /*verify(bookRepository, times(1)).save(captor.capture());
        Book bookSaving = captor.getValue();*/

        //Assert for book
        /*assertEquals(book1.getId(), bookSaving.getId());
        assertEquals(book1.getIsbn(), bookSaving.getIsbn());
        assertEquals(book1.getNameAuthor(), bookSaving.getNameAuthor());
        assertEquals(book1.getBookTitle(), bookSaving.getBookTitle());
        assertEquals(book1.getNameIllustrator(), bookSaving.getNameIllustrator());
        assertEquals(book1.getSuitableAge(), bookSaving.getSuitableAge());*/

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

        //book1 = new Book(1000L, 98765, "Kleine onderzoekers voertuigen", "Ruth Martin", "Ruth Martin", 4);
        //Arrange
        InformationBook informationBook1 = new InformationBook();
        informationBook1.setEducationLevel("beginners");
        informationBook1.setCurrentTopic("adventure");

        Book book1 = new Book();
        book1.setId(1000L);
        book1.setIsbn(98765);
        book1.setBookTitle("Kleine onderzoekers voertuigen");
        book1.setNameAuthor("Ruth Martin");
        book1.setNameIllustrator("Ruth Martin");
        book1.setSuitableAge(4);
        book1.setInformationBook(informationBook1);

        //book2 = new Book(1001L, 8765, "Graafmachines en kiepautos", "Angela Royston", "David Barrow", 4);
        InformationBook informationBook2 = new InformationBook();
        informationBook2.setEducationLevel("beginners");
        informationBook2.setCurrentTopic("transport");

        Book book2 = new Book();
        book2.setId(1001L);
        book2.setIsbn(8765);
        book2.setBookTitle("Graafmachines en kiepautos");
        book2.setNameAuthor("Angela Royston");
        book2.setNameIllustrator("David Barrow");
        book2.setSuitableAge(4);
        book2.setInformationBook(informationBook2);

        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);

        //Act
        //Mockito.when(bookRepository.findAllBooksByCurrentTopic("transport")).thenReturn(bookList);

        //List<BookDto> bookDto = bookService.showAllBooksByTopic("transport");


        //Assert


        //Arrange
        /*InformationBook informationBook = new InformationBook("beginners", "adventure");
        ReadingBook readingBook = new ReadingBook("dutch", "adventure", "basic");
        BookDto bookDto = new BookDto(1003L, 62983, "Woeste Willem", "Ingrid Schubert", "Dieter Schubert", 5, informationBook, readingBook);
        Book book = new Book(1003L, 62983, "Woeste Willem", "Ingrid Schubert", "Dieter Schubert", 5, informationBook, readingBook);

        when(bookRepository.existsById(1003L)).thenReturn(true);
        when(bookRepository.findAllBooksByCurrentTopic(informationBook.getCurrentTopic())).thenReturn(List.of(book));*/

        //Act
        /*List<Book> bookList = bookRepository.findAllBooksByCurrentTopic("humor");
        List<InformationBookDto> bookDtoList = bookService.showAllBooksByTopic(bookList.get(0).getInformationBook().getCurrentTopic());*/
        //Assert
        /*assertEquals(book.getInformationBook().getCurrentTopic(), bookDtoList.get(0).getCurrentTopic());*/
    }

    @Test
    @DisplayName("Should delete by id")
    void deleteById() {
        //Arrange
        when(bookRepository.existsById(1000L)).thenReturn(true);
        when(bookRepository.existsById(null)).thenReturn(false);
        when(bookRepository.findById(1000L)).thenReturn(Optional.of(book1));
        //Act
        bookService.deleteById(1000L);
        //Assert
        verify(bookRepository).delete(book1);
        //Assert in case the book is not found
        assertThrows(IdNotFoundException.class, () -> {
            bookService.deleteById(null);
        });
    }

    @Test
    @DisplayName("Should update one book")
    void updateOneBook() {
        //Arrange
        BookDto bookDto = new BookDto(1003L, 62983, "Woeste Willem", "Ingrid Schubert", "Dieter Schubert", 5, informationBook1, readingBook1);
        Book book = new Book(1003L, 62983, "Woeste Willem", "Ingrid Schubert", "Dieter Schubert", 5, informationBook1, readingBook1);
        Book book5 = new Book(1003L, 62983, "Woeste Willem", "Ingrid Schubert", "Dieter Schubert", 5, informationBook2, readingBook1);

        when(bookRepository.findById(1003L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any())).thenReturn(book5);
        //Act
        bookService.updateOneBook(1003L, bookDto);

        //Assert
        verify(bookRepository, times(1)).save(captor.capture());
        Book captured = captor.getValue();
        assertEquals(book.getBookTitle(), captured.getBookTitle());
        assertEquals(book.getNameAuthor(), captured.getNameAuthor());
        assertEquals(book.getNameIllustrator(), captured.getNameIllustrator());
        assertEquals(book.getId(), captured.getId());
        assertEquals(book.getIsbn(), captured.getIsbn());
        assertEquals(book.getSuitableAge(), captured.getSuitableAge());

        assertThrows(IdNotFoundException.class, () -> {
            bookService.updateOneBook(null, null);
        });
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