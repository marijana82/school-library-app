package com.marijana.library1223.services;

import com.marijana.library1223.dtos.BookDto;
import com.marijana.library1223.exceptions.IdNotFoundException;
import com.marijana.library1223.exceptions.RecordNotFoundException;
import com.marijana.library1223.models.Book;
import com.marijana.library1223.models.FileDocument;
import com.marijana.library1223.repositories.BookRepository;
import com.marijana.library1223.repositories.FileUploadRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
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
    FileUploadRepository fileUploadRepository;

    @InjectMocks
    BookService bookService;

    @Captor
    ArgumentCaptor<Book> captor;

    Book book;
    Book book1;
    Book book2;
    Book book3;
    Book book4;

    BookDto bookDto;
    BookDto bookDto1;
    BookDto bookDto2;
    BookDto bookDto3;
    BookDto bookDto4;


    //load general test-data before the start of each test
    @BeforeEach
    void setUp() {

        //set-up data for book
        book1 = new Book(1000L, 98765, "Kleine onderzoekers voertuigen", "Ruth Martin", "Ruth Martin", 4);
        book2 = new Book(1001L, 8765, "Graafmachines en kiepautos", "Angela Royston", "David Barrow", 4);
        book3 = new Book(1002L, 763987, "Barbapapa", "Annette Tilson", "Talus Taylor", 5 );
        book4 = new Book(1003L, 62983, "Woeste Willem", "Ingrid Schubert", "Dieter Schubert", 5);

    }

    //clean up the database
    @AfterEach
    void tearDown() {
    }

    //tests
    @Test
    @DisplayName("Should create new book")
    void createNewBook() {

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

        BookDto bookDto1 = bookService.createNewBook(bookDto);

        assertEquals(12345, bookDto1.getIsbn());
        assertEquals("Mathilda", bookDto1.getBookTitle());
        assertEquals("Roald Dahl", bookDto1.getNameAuthor());
        assertEquals("Quentin Blake", bookDto1.getNameIllustrator());
        assertEquals(9, bookDto1.getSuitableAge());

    }



    @Test
    @DisplayName("Should show one book")
    void showOneBook() {

        FileDocument file1 = new FileDocument();
        file1.setFileName("bookPhoto1");

        Book book1 = new Book();
        book1.setId(1L);
        book1.setIsbn(12345);
        book1.setBookTitle("Book1");
        book1.setNameAuthor("Author1");
        book1.setNameIllustrator("Illustrator1");
        book1.setSuitableAge(8);
        book1.setBookPhoto(file1);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));

        BookDto bookDto = bookService.showOneBook(1L);

        assertEquals(book1.getBookTitle(), bookDto.getBookTitle());

        assertThrows(RecordNotFoundException.class, () -> {
            bookService.showOneBook(null);
        });
    }

    @Test
    @DisplayName("Should show all books")
    void showAllBooks() {

        when(bookRepository.findAll()).thenReturn(List.of(book1, book2));

        List<BookDto> bookDtoList = bookService.showAllBooks();

        assertEquals(book1.getBookTitle(), bookDtoList.get(0).getBookTitle());
        assertEquals(book2.getBookTitle(), bookDtoList.get(1).getBookTitle());
    }


    @Test
    @DisplayName("Should show all books by name author")
    void showAllBooksByNameAuthor() {

        when(bookRepository.findAllBooksByNameAuthorEqualsIgnoreCase("Ruth Martin")).thenReturn(List.of(book1));

        List<BookDto> bookDtoList = bookService.showAllBooksByNameAuthor("Ruth Martin");

        assertEquals(book1.getNameAuthor(), bookDtoList.get(0).getNameAuthor());

    }

    @Test
    @DisplayName("Should show all books by name illustrator")
    void showAllBooksByNameIllustrator() {

        when(bookRepository.findAllBooksByNameIllustratorEqualsIgnoreCase("David Barrow")).thenReturn(List.of(book2));

        List<BookDto> bookDtoList = bookService.showAllBooksByNameIllustrator("David Barrow");

        assertEquals(book2.getNameIllustrator(), bookDtoList.get(0).getNameIllustrator());
    }

    @Test
    @DisplayName("Should show all books by name illustrator and name author")
    void showAllBooksByNameIllustratorAndNameAuthor() {

        when(bookRepository.findAllBooksByNameIllustratorAndNameAuthorEqualsIgnoreCase("Talus Taylor", "Annette Tilson")).thenReturn(List.of(book3));

        List<BookDto> bookDtoList = bookService.showAllBooksByNameIllustratorAndNameAuthor("Talus Taylor", "Annette Tilson");

        assertEquals(book3.getNameIllustrator(), bookDtoList.get(0).getNameIllustrator());
        assertEquals(book3.getNameAuthor(), bookDtoList.get(0).getNameAuthor());

    }


    @Test
    @DisplayName("Should delete by id")
    void deleteById() {

        when(bookRepository.existsById(1000L)).thenReturn(true);
        when(bookRepository.existsById(null)).thenReturn(false);
        when(bookRepository.findById(1000L)).thenReturn(Optional.of(book1));

        bookService.deleteById(1000L);

        verify(bookRepository).delete(book1);

        assertThrows(IdNotFoundException.class, () -> {
            bookService.deleteById(null);
        });
    }

    @Test
    @DisplayName("Should update one book")
    void updateOneBook() {

        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setIsbn(12345);
        bookDto.setBookTitle("Book 1");
        bookDto.setNameAuthor("Author 1");
        bookDto.setNameIllustrator("Illustrator 1");
        bookDto.setSuitableAge(10);

        Book book = new Book();
        book.setId(1L);
        book.setIsbn(12345);
        book.setBookTitle("Book 1");
        book.setNameAuthor("Author 1");
        book.setNameIllustrator("Illustrator 1");
        book.setSuitableAge(10);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setIsbn(12345);
        book2.setBookTitle("Book 2");
        book2.setNameAuthor("Author 2");
        book2.setNameIllustrator("Illustrator 2");
        book2.setSuitableAge(12);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.existsById(1L)).thenReturn(true);
        when(bookRepository.save(any())).thenReturn(book2);

        bookService.updateOneBook(1L, bookDto);

        verify(bookRepository, times(1)).save(captor.capture());
        Book captured = captor.getValue();

        assertEquals(book.getId(), captured.getId());
        assertEquals(book.getBookTitle(), captured.getBookTitle());
        assertEquals(book.getNameAuthor(), captured.getNameAuthor());
        assertEquals(book.getNameIllustrator(), captured.getNameIllustrator());
        assertEquals(book.getIsbn(), captured.getIsbn());
        assertEquals(book.getSuitableAge(), captured.getSuitableAge());

        assertThrows(IdNotFoundException.class, () -> {
            bookService.updateOneBook(null, null);
        });
    }

    @Test
    @DisplayName("Should update book partially")
    void updateBookPartially() {

        FileDocument file1 = new FileDocument();
        file1.setFileName("bookPhoto1");

        FileDocument file2 = new FileDocument();
        file2.setFileName("bookPhoto2");

        Book book1 = new Book();
        book1.setId(1L);
        book1.setIsbn(12345);
        book1.setBookTitle("Book 1");
        book1.setNameAuthor("Author 1");
        book1.setNameIllustrator("Illustrator 1");
        book1.setSuitableAge(10);
        book1.setBookPhoto(file1);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setIsbn(12345);
        book2.setBookTitle("Book 2");
        book2.setNameAuthor("Author 2");
        book2.setNameIllustrator("Illustrator 2");
        book2.setSuitableAge(12);
        book2.setBookPhoto(file2);

        BookDto bookDto1 = new BookDto();
        bookDto1.setId(1L);
        bookDto1.setIsbn(12345);
        bookDto1.setBookTitle("Book 1");
        bookDto1.setNameAuthor("Author 1");
        bookDto1.setNameIllustrator("Illustrator 1");
        bookDto1.setSuitableAge(10);
        bookDto1.setBookPhoto(file1);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
        when(bookRepository.existsById(1L)).thenReturn(true);
        when(bookRepository.save(any(Book.class))).thenReturn(book2);

        bookService.updateBookPartially(1L, bookDto1);

        verify(bookRepository, times(1)).save(captor.capture());
        Book captured = captor.getValue();

        assertEquals(book1.getId(), captured.getId());
        assertEquals(book1.getBookTitle(), captured.getBookTitle());
        assertEquals(book1.getSuitableAge(), captured.getSuitableAge());
        assertEquals(book1.getNameAuthor(), captured.getNameAuthor());
        assertEquals(book1.getNameIllustrator(), captured.getNameIllustrator());
    }

    @Test
    @DisplayName("Should throw Id Not Found Exception In Update Book Partially")
    void IdNotFoundExceptionTest() {
        assertThrows(IdNotFoundException.class, () -> bookService.updateBookPartially(1L, new BookDto(3L, 2345, "Book3", "Author3", "Illustrator3", 10)));
    }

    @Test
    @DisplayName("Should assign photo to book")
    void assignPhotoToBook() {

        Book book = new Book();
        book.setId(1L);

        FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName("photo");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(fileUploadRepository.findByFileName("photo")).thenReturn(Optional.of(fileDocument));

        bookService.assignPhotoToBook(fileDocument.getFileName(), book.getId());

        assertEquals(fileDocument, book.getBookPhoto());

        assertThrows(RecordNotFoundException.class, () -> {
            bookService.assignPhotoToBook("picture", 1L);
        });


    }


    @Test
    @Disabled
    void transferBookToBookDto() {
    }

    @Test
    @Disabled
    void transferBookDtoToBook() {
    }

}